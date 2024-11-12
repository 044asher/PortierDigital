package org.example.portierdigital.AdminPart.AService;

import org.example.portierdigital.General.Model.Portfolio.MyWorks;
import org.example.portierdigital.AdminPart.ARepository.MyWorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyWorksService {
    private final MyWorksRepository myWorksRepository;

    @Autowired
    public MyWorksService(MyWorksRepository myWorksRepository) {
        this.myWorksRepository = myWorksRepository;
    }

    public Page<MyWorks> findAll(Pageable pageable) {
        return myWorksRepository.findAll(pageable);
    }

    public MyWorks save(MyWorks myWork) {
       return myWorksRepository.save(myWork);
    }

    public Optional<MyWorks> findById(Long id) {
        return myWorksRepository.findById(id);
    }

    public void deleteById(Long id) {
        myWorksRepository.deleteById(id);
    }
}
