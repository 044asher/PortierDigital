package org.example.portierdigital.AdminPart.AController.EmailTemplates;

import org.example.portierdigital.AdminPart.ARepository.EmailTemplateRepository;
import org.example.portierdigital.General.Model.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/email-templates")
public class EmailTemplatesController {
    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public EmailTemplatesController(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @GetMapping
    public String emailTemplates() {
        return "admin/email-templates/list";
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getEmailTemplates(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmailTemplate> emailTemplates = emailTemplateRepository.findAll(pageable);

        List<EmailTemplate> requests = emailTemplates.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("requests", requests);
        response.put("totalPages", emailTemplates.getTotalPages());
        response.put("totalElements", emailTemplates.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmailTemplate(@PathVariable Long id) {
        emailTemplateRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<EmailTemplate> createEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
        EmailTemplate savedTemplate = emailTemplateRepository.save(emailTemplate);
        return ResponseEntity.ok(savedTemplate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmailTemplate> updateEmailTemplate(@PathVariable Long id, @RequestBody EmailTemplate emailTemplate) {
        emailTemplate.setId(id);
        EmailTemplate updatedTemplate = emailTemplateRepository.save(emailTemplate);
        return ResponseEntity.ok(updatedTemplate);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmailTemplate> getEmailTemplateById(@PathVariable Long id) {
        Optional<EmailTemplate> emailTemplate = emailTemplateRepository.findById(id);
        return emailTemplate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

}
