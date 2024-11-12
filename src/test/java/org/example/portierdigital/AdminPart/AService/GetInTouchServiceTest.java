package org.example.portierdigital.AdminPart.AService;

import org.example.portierdigital.AdminPart.ARepository.GetInTouchRepository;
import org.example.portierdigital.General.Model.GetInTouch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetInTouchServiceTest {

    @Mock
    private GetInTouchRepository getInTouchRepository;

    @InjectMocks
    private GetInTouchService getInTouchService;

    @Test
    void testSave() {
        GetInTouch getInTouch = new GetInTouch();
        getInTouchService.save(getInTouch);
        verify(getInTouchRepository, times(1)).save(getInTouch);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<GetInTouch> getInTouchPage = new PageImpl<>(List.of(new GetInTouch(), new GetInTouch()));

        when(getInTouchRepository.findAll(pageable)).thenReturn(getInTouchPage);

        Page<GetInTouch> result = getInTouchService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(getInTouchRepository, times(1)).findAll(pageable);
    }

    @Test
    void testDeleteById(){
        Long id = 1L;
        getInTouchService.deleteById(id);
        verify(getInTouchRepository, times(1)).deleteById(id);
    }
}
