package org.example.portierdigital.General.Config.Advices;

import org.example.portierdigital.General.Config.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUsername(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails userDetails) {
           if(userDetails.getUsername() != null) {
               model.addAttribute("username", userDetails.getUsername());
           }
            model.addAttribute("role", userDetails);
        }
    }
}
