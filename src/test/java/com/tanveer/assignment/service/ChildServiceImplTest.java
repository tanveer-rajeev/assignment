package com.tanveer.assignment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.dto.ChildResponseDto;
import com.tanveer.assignment.model.Child;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.repository.ChildRepository;
import com.tanveer.assignment.repository.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChildServiceImplTest {

    @Mock
    private ChildRepository childRepository;

    @Mock
    private ParentRepository parentRepository;

    @InjectMocks
    private ChildServiceImpl childService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        childService = new ChildServiceImpl(childRepository, parentRepository);
    }

    @Test
    void testGetAllChild() {

        when(childRepository.findAll()).thenReturn(Arrays.asList(new Child(), new Child()));

        List<Child> result = childService.getAllChild();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetChildById() throws CustomException {
        // Arrange
        int childId = 1;
        Child child = new Child();
        child.setId(childId);
        child.setFirstName("John");
        child.setLastName("Doe");

        Parent parent = new Parent();
        parent.setState("California");
        parent.setStreet("123 Main St");
        parent.setCity("Los Angeles");
        parent.setZip("90001");
        child.setParent(parent);

        when(childRepository.findById(childId)).thenReturn(Optional.of(child));

        ChildResponseDto result = childService.getChildById(childId);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("California", result.getState());
        assertEquals("123 Main St", result.getStreet());
        assertEquals("Los Angeles", result.getCity());
        assertEquals("90001", result.getZip());
    }

    @Test
    void testSaveChild() throws CustomException {

        // Arrange
        int parentId = 1;
        Parent parent = new Parent();
        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parent));

        ArgumentCaptor<Child> childCaptor = ArgumentCaptor.forClass(Child.class);

        childService.saveChild(new Child(), parentId);

        verify(childRepository).save(childCaptor.capture());
        assertEquals(parent, childCaptor.getValue().getParent(), "The parent reference in the saved child is not as expected");

    }

    @Test
    void testUpdateChild() throws CustomException {

        int childId = 1;
        Child existingChild = new Child();
        existingChild.setId(childId);
        existingChild.setFirstName("John");

        Child updatedChildDto = new Child();
        updatedChildDto.setFirstName("Doe");

        when(childRepository.findById(childId)).thenReturn(Optional.of(existingChild));

        childService.updateChild(updatedChildDto, childId);


        assertEquals("Doe", existingChild.getFirstName());
    }

    @Test
    void testDeleteChild() {

        int childId = 1;
        Child child = new Child();
        child.setId(childId);

        when(childRepository.findById(childId)).thenReturn(Optional.of(child));

        assertDoesNotThrow(() -> childService.deleteChild(childId));

        verify(childRepository, times(1)).deleteById(childId);
    }
}

