package org.example.portierdigital.AdminPart.AService;

import org.example.portierdigital.AdminPart.ARepository.GetInTouchRepository;
import org.example.portierdigital.General.Model.GetInTouch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetInTouchService {
    private final GetInTouchRepository getInTouchRepository;

    @Autowired
    public GetInTouchService(GetInTouchRepository getInTouchRepository) {
        this.getInTouchRepository = getInTouchRepository;
    }

    public void save(GetInTouch getInTouch) {
        getInTouchRepository.save(getInTouch);
    }

    public Page<GetInTouch> findAll(Pageable pageable) {
        return getInTouchRepository.findAll(pageable);
    }

    public void deleteById(Long id) {
        getInTouchRepository.deleteById(id);
    }
}
