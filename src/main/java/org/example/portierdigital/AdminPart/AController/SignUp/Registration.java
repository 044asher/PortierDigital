package org.example.portierdigital.AdminPart.AController.SignUp;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.portierdigital.General.Config.UserRepository;
import org.example.portierdigital.General.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@Slf4j
public class Registration {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Registration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "admin/sign-up";
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<String> registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
            return ResponseEntity.badRequest().body("Email already exists");
        }

        user.setDateOfRegistration(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_UNCHECKED");

        userRepository.save(user);

        return ResponseEntity.ok("Success");
    }

}
