package org.example.portierdigital.AdminPart.AController.GetInTouch;

import jakarta.validation.Valid;
import org.example.portierdigital.AdminPart.AService.GetInTouchService;
import org.example.portierdigital.General.Model.GetInTouch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/get-in-touch")
public class GetInTouchController {
    private final GetInTouchService getInTouchService;

    @Autowired
    public GetInTouchController(GetInTouchService getInTouchService) {
        this.getInTouchService = getInTouchService;
    }

    @GetMapping
    public String getInTouch(){
        return "admin/get-in-touch/get-in-touch";
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveGetInTouch(@RequestBody @Valid GetInTouch getInTouch) {
        getInTouchService.save(getInTouch);
        return ResponseEntity.ok("Data saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getGetInTouchRecords(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GetInTouch> getInTouchPage = getInTouchService.findAll(pageable);

        List<GetInTouch> records = getInTouchPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("records", records);
        response.put("totalPages", getInTouchPage.getTotalPages());
        response.put("totalElements", getInTouchPage.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-get-in-touch/{id}")
    public ResponseEntity<Void> deleteGetInTouchRecord(@PathVariable Long id) {
        getInTouchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
