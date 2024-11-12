package org.example.portierdigital.UserPart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Blog {

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
