package bits.project.bookexchangeplatform.controller;

import bits.project.bookexchangeplatform.dto.UserToken;
import bits.project.bookexchangeplatform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bits.project.bookexchangeplatform.dto.UserDto;
import bits.project.bookexchangeplatform.dto.LoginDto;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationControllers {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        try {
            authService.register(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be registered.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        try{
            return ResponseEntity.ok(authService.login(loginDto));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        String message = authService.logout(token);
        return ResponseEntity.ok(message);
    }

    @PostMapping(   "/reset-password-request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        try {
            String response = authService.requestPasswordReset(email);
            if(!response.startsWith("Invalid")){
                response= "http://localhost:8080/reset-password?token=" + response;
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during password reset request.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String password) {
        try {
            authService.resetPassword(token, password);
            return ResponseEntity.ok("Password reset successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Password reset failed.");
        }
    }

        @GetMapping("/user-details")
    public ResponseEntity<String> getUserDetails(@RequestParam String email){
        try {
            String userDetails = authService.getUserDetails(email);
            return ResponseEntity.ok(userDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Password reset failed.");
        }
    }
}
