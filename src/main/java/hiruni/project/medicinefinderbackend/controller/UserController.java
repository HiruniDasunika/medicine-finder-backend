//package hiruni.project.medicinefinderbackend.controller;
//
//import hiruni.project.medicinefinderbackend.dtd.ResponseDTD;
//import hiruni.project.medicinefinderbackend.entity.Auth;
//import hiruni.project.medicinefinderbackend.entity.User;
//import hiruni.project.medicinefinderbackend.service.AuthService;
//import hiruni.project.medicinefinderbackend.service.UserService;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.providers.encoding.Md5PasswordEncoder;
//import org.springframework.security.providers.encoding.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//import java.util.Map;
//
//@RestController
//public class UserController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping(name = "register", path = "/register")
//    @ResponseBody
//    public ResponseEntity<ResponseDTD<User>> registerUser(@RequestBody Map<String,String> requestBody) {
//
//        try {
//            System.out.println(requestBody);
//            Auth existAuth = this.authService.getAuthByEmail(requestBody.get("email"));
//            if (existAuth == null) {
//                User user = new User(requestBody.get("name"), requestBody.get("address"), requestBody.get("userType"));
//
//                PasswordEncoder encoder = new Md5PasswordEncoder();
//
//                User savedUser = this.userService.saveUser(user);
//
//                if( savedUser != null) {
//                    Auth auth = new Auth(requestBody.get("email"), encoder.encodePassword(requestBody.get("password"), null));
//                    auth.setUser(savedUser);
//                    if(this.authService.saveAuth(auth) != null) {
//                        ResponseDTD<User> response = new ResponseDTD<>("success", "Registered Successfully", null);
//                        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//                    } else {
//                        ResponseDTD<User> response = new ResponseDTD<>("fail", "Something Went Wrong", null);
//                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                } else {
//                    ResponseDTD<User> response = new ResponseDTD<>("fail", "Missing Data", null);
//                    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//                }
//            } else {
//                ResponseDTD<User> response = new ResponseDTD<>("fail", "Already Exist User", null);
//                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//            }
//        }
//        catch (DataAccessException e) {
//            System.out.println(e.getMessage());
//            ResponseDTD<User> response = new ResponseDTD<>("fail", "Internal Server Error", null);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
////    public ResponseEntity<Response> loginUser()
//}
