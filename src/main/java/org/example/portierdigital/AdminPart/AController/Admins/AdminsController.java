package org.example.portierdigital.AdminPart.AController.Admins;

import org.example.portierdigital.AdminPart.ARepository.EmailTemplateRepository;
import org.example.portierdigital.General.Config.UserRepository;
import org.example.portierdigital.General.Model.EmailTemplate;
import org.example.portierdigital.General.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    private final UserRepository userRepository;
    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public AdminsController(UserRepository userRepository, EmailTemplateRepository emailTemplateRepository) {
        this.userRepository = userRepository;
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @GetMapping("/all")
    public String adminsPage() {
        return "/admin/admin";
    }

    private ResponseEntity<Map<String, Object>> getUsersByRole(int page, int size, String role) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> requestPage = userRepository.findByRole(role, pageable);

        List<UserDto> requests = UserMapper.toUserDto(requestPage.getContent());

        Map<String, Object> response = new HashMap<>();
        response.put("requests", requests);
        response.put("totalPages", Optional.of(requestPage.getTotalPages()));
        response.put("totalElements", Optional.of(requestPage.getTotalElements()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admins")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAdmins(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        return getUsersByRole(page, size, "ROLE_ADMIN");
    }

    @GetMapping("/requests")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAdminRequests(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size) {
        return getUsersByRole(page, size, "ROLE_UNCHECKED");
    }


    @GetMapping("/requests/count")
    @ResponseBody
    public Long getRequestsCount() {
        return userRepository.countByRole("ROLE_UNCHECKED");
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @GetMapping("/email-templates/all")
    @ResponseBody
    public List<EmailTemplate> getAllTemplates() {
        return emailTemplateRepository.findAll();
    }

    @GetMapping("/email-templates/view/{id}")
    @ResponseBody
    public EmailTemplate getTemplateById(@PathVariable Long id) {
        return emailTemplateRepository.findById(id).orElseThrow();
    }


    @DeleteMapping("/reject-application/{id}")
    public ResponseEntity<Void> rejectApplication(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/approve/{id}")
    @ResponseBody
    public ResponseEntity<String> approveUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setRole("ROLE_ADMIN");
        userRepository.save(user);

        return ResponseEntity.ok("User role updated to ROLE_ADMIN successfully.");
    }



}
