package hiruni.project.medicinefinderbackend.auth;

import hiruni.project.medicinefinderbackend.config.JwtService;
import hiruni.project.medicinefinderbackend.dtd.AuthResponse;
import hiruni.project.medicinefinderbackend.dtd.ResponseDTD;
import hiruni.project.medicinefinderbackend.entity.Email;
import hiruni.project.medicinefinderbackend.entity.Role;
import hiruni.project.medicinefinderbackend.entity.User;
import hiruni.project.medicinefinderbackend.entity.Verification;
import hiruni.project.medicinefinderbackend.repository.UserRepository;
import hiruni.project.medicinefinderbackend.repository.VerificationRepository;
import hiruni.project.medicinefinderbackend.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;
    private final VerificationRepository verificationRepository;

    private String url = "http://localhost:4200/verify-email";

    public ResponseDTD<String> register(RegisterRequest request) throws MessagingException {
        System.out.println(request.getRole());
        System.out.println(Role.AGENCY.getRole());

        Role role = Role.AGENCY;

        if(request.getRole().toString() == Role.CUSTOMER.getRole().toString()) {
            role = Role.CUSTOMER;
        }
        var user = User.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .passw(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        repository.save(user);

        this.sendEmail(request.getEmail(),user);

//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
        ResponseDTD<String> response = new ResponseDTD<>(true, "Registered Successfully", "{}");
        return response;
    }

    private void sendEmail(String email, User user) throws MessagingException {
        Email body = new Email();
        body.setRecipient(email);
        body.setSubject("Verify your email address");
        String token = this.generateVerificationLink(user);
        Context context = new Context();
        context.setVariable("token",token);
        context.setVariable("name", user.getName());
        emailService.sendHtmlMail(body.getRecipient(), body.getSubject(), "verification",context);
    }

    private String generateVerificationLink(User user) {
        Verification newVerificationUserRequest = new Verification();
        newVerificationUserRequest.setUser(user);

        String token = UUID.randomUUID().toString();

        newVerificationUserRequest.setToken(token);
        verificationRepository.save(newVerificationUserRequest);

        return token;

    }

    public ResponseDTD<String> verifyEmailAddress(VerificationRequest request) {
        ResponseDTD<String> response;
        try {
            var verification = verificationRepository.findByToken(request.getToken()).orElseThrow();

            verification.setStatus(true);

            verificationRepository.save(verification);
            response = new ResponseDTD<>(true, "Verified Successfully", "{}");
        } catch (NoSuchElementException e) {
            response = new ResponseDTD<>(false, "Verification Failed", "{}");
        }

        return response;
    }

    public ResponseDTD<AuthResponse> login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse(jwtToken);
        ResponseDTD<AuthResponse> response = new ResponseDTD<>(true, "Logged Successfully", authResponse);
        return response;
    }
}
