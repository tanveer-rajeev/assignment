package com.tanveer.assignment.service;

import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.repository.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ParentServiceImplTest {

    @Mock
    private ParentRepository parentRepository;

    @InjectMocks
    private ParentServiceImpl parentService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parentService = new ParentServiceImpl(parentRepository);
    }
    @Test
    void getAllParents() {

        List<Parent> parents = new ArrayList<>();
        when(parentRepository.findAll()).thenReturn(parents);

        List<Parent> result = parentService.getAllParents();

        assertEquals(parents, result);
    }

    @Test
    void getParentById() throws CustomException {

        Parent parent = new Parent();
        when(parentRepository.findById(1)).thenReturn(Optional.of(parent));

        Parent result = parentService.getParentById(1);

        assertEquals(parent, result);
    }


    @Test
    void saveParent() {

        Parent parent = new Parent();
        when(parentRepository.save(any(Parent.class))).thenReturn(parent);

        Parent result = parentService.saveParent(parent);

        assertEquals(parent, result);
        verify(parentRepository, times(1)).save(any(Parent.class));
    }

    @Test
    void updateParent() throws CustomException {

        Parent existingParent = new Parent();
        existingParent.setId(1);
        existingParent.setFirstName("John");
        existingParent.setLastName("Doe");

        Parent parentDto = new Parent();
        parentDto.setId(1);
        parentDto.setFirstName("Updated");

        when(parentRepository.findById(1)).thenReturn(Optional.of(existingParent));
        when(parentRepository.save(any(Parent.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Parent result = parentService.updateParent(parentDto, 1);

        assertEquals(parentDto.getId(), result.getId());
        assertEquals(parentDto.getFirstName(), result.getFirstName());
        assertEquals(existingParent.getLastName(), result.getLastName());
    }

    @Test
    void deleteParent() throws CustomException {

        Parent existingParent = new Parent();
        existingParent.setId(1);

        when(parentRepository.findById(1)).thenReturn(Optional.of(existingParent));

        parentService.deleteParent(1);

        verify(parentRepository, times(1)).deleteById(1);
    }

}
