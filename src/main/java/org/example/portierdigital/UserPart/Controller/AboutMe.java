package org.example.portierdigital.UserPart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMe {
    @GetMapping("/about-me")
    public String aboutMe() {
       return "about";
    }
}
