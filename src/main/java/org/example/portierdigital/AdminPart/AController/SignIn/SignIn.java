package org.example.portierdigital.AdminPart.AController.SignIn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignIn {

    @GetMapping("/sign-in")
    public String signIn() {
        return "admin/sign-in";
    }
}
