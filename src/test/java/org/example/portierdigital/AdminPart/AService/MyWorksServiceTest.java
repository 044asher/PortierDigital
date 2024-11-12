package org.example.portierdigital.AdminPart.AService;

import org.example.portierdigital.AdminPart.ARepository.MyWorksRepository;
import org.example.portierdigital.General.Model.Portfolio.MyWorks;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyWorksServiceTest {
    @Mock
    private MyWorksRepository myWorksRepository;

    @InjectMocks
    private MyWorksService myWorksService;

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MyWorks> myWorksPage = new PageImpl<>(List.of(new MyWorks(), new MyWorks()));

        when(myWorksRepository.findAll(pageable)).thenReturn(myWorksPage);

        Page<MyWorks> result = myWorksService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(myWorksRepository, times(1)).findAll(pageable);
    }

    @Test
    void testSave(){
        MyWorks myWorks = new MyWorks();
        myWorksService.save(myWorks);
        verify(myWorksRepository, times(1)).save(myWorks);
    }

    @Test
    void testFindById(){
        MyWorks myWorks = new MyWorks();
        when(myWorksService.findById(1L)).thenReturn(Optional.of(myWorks));

        Optional<MyWorks> result = myWorksService.findById(1L);

        assertNotNull(result);
        assertEquals(myWorks, result.get());
        verify(myWorksRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteById(){
        Long id = 1L;
        myWorksService.deleteById(id);
        verify(myWorksRepository, times(1)).deleteById(id);
    }
}
