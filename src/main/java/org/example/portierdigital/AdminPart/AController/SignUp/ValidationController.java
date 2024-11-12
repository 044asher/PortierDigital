package org.example.portierdigital.AdminPart.AController.SignUp;

import org.example.portierdigital.General.Config.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/validate")
public class ValidationController {
    private final UserRepository userRepository;

    @Autowired
    public ValidationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/username")
    public ResponseEntity<String> validateUsername(@RequestBody Map<String, String> request) {
        String username = request.get("username");

        if(username == null || username.length() < 2 || username.length() > 30) {
            return ResponseEntity.badRequest().body("Username must be between 2 and 30 characters");
        }

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        return ResponseEntity.ok().body("Valid");
    }

    @PostMapping("/email")
    public ResponseEntity<String> validateEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            return ResponseEntity.badRequest().body("Email must be valid");
        }

        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        return ResponseEntity.ok().body("Valid");
    }
}
