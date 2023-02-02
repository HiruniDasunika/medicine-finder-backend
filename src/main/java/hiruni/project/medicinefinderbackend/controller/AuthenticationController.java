package hiruni.project.medicinefinderbackend.controller;

import hiruni.project.medicinefinderbackend.auth.*;
import hiruni.project.medicinefinderbackend.dtd.AuthResponse;
import hiruni.project.medicinefinderbackend.dtd.ResponseDTD;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
    @PostMapping("/register")
    public ResponseEntity<ResponseDTD<String>> register(
            @RequestBody RegisterRequest request
    ) throws MessagingException {
        return ResponseEntity.ok(service.register(request));
    }
    @CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
    @PostMapping("/login")
    public ResponseEntity<ResponseDTD<AuthResponse>> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.login(request));
    }
    @CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
    @PostMapping("/verify-email")
    public ResponseEntity<ResponseDTD<String>> verifyEmail(
            @RequestBody VerificationRequest request
    ){
        return ResponseEntity.ok(service.verifyEmailAddress(request));
    }
}
