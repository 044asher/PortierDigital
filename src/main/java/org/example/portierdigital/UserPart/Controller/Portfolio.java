package org.example.portierdigital.UserPart.Controller;

import org.example.portierdigital.General.Model.Portfolio.MyWorks;
import org.example.portierdigital.AdminPart.AService.MyWorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Portfolio {

    private final MyWorksService myWorksService;

    @Autowired
    public Portfolio(MyWorksService myWorksService) {
        this.myWorksService = myWorksService;
    }

    @GetMapping("/portfolio")
    public String portfolio(){
        return "portfolio";
    }

    @ResponseBody
    @GetMapping("/get-work")
    public Page<MyWorks> getWork(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return myWorksService.findAll(pageable);
    }
}
