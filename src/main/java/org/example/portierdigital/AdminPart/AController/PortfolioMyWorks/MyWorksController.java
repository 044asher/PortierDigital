package org.example.portierdigital.AdminPart.AController.PortfolioMyWorks;

import org.example.portierdigital.AdminPart.AService.MyWorksService;
import org.example.portierdigital.General.Model.Portfolio.MyWorks;
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
import java.util.Optional;

@Controller
@RequestMapping("/admin/my-works")
public class MyWorksController {
    private final MyWorksService myWorksService;

    @Autowired
    public MyWorksController(MyWorksService myWorksService) {
        this.myWorksService = myWorksService;
    }

    @GetMapping
    public String myWorks() {
        return "admin/portfolioMyWorks/my-works";
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getMyWorks(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MyWorks> myWorks = myWorksService.findAll(pageable);

        List<MyWorks> myWorksList = myWorks.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("myWorks", myWorksList);
        response.put("totalPages", myWorks.getTotalPages());
        response.put("totalElements", myWorks.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addMyWork(@RequestBody MyWorks myWork) {
            myWorksService.save(myWork);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-work/{id}")
    public ResponseEntity<MyWorks> getMyWork(@PathVariable Long id) {
        Optional<MyWorks> myWork = myWorksService.findById(id);
        return myWork.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update-work/{id}")
    public ResponseEntity<MyWorks> updateMyWork(@PathVariable Long id, @RequestBody MyWorks myWork) {
        myWork.setId(id);
        MyWorks newMyWork = myWorksService.save(myWork);
        return ResponseEntity.ok(newMyWork);
    }

    @DeleteMapping("/delete-work/{id}")
    public ResponseEntity<Void> deleteMyWork(@PathVariable Long id) {
        myWorksService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
