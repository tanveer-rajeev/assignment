package com.tanveer.assignment.service;

import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.dto.ChildResponseDto;
import com.tanveer.assignment.model.Child;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.repository.ChildRepository;
import com.tanveer.assignment.repository.ParentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;

    @Override
    public List<Child> getAllChild() {
        return childRepository.findAll();
    }

    @Override
    public ChildResponseDto getChildById(Integer id) throws CustomException {
        Child child = childRepository.findById(id).orElseThrow(() -> new CustomException("Child not found"));
        return ChildResponseDto.builder()
                .firstName(child.getFirstName())
                .lastName(child.getLastName())
                .state(child.getParent().getState())
                .street(child.getParent().getStreet())
                .city(child.getParent().getCity())
                .zip(child.getParent().getZip())
                .build();
    }

    @Override
    public Child saveChild(Child user, Integer parentId) throws CustomException {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new CustomException("Parent not found"));
        user.setParent(parent);
        return childRepository.save(user);
    }

    @Override
    @Transactional
    public void updateChild(Child childDto, Integer id) throws CustomException {
        Child child = childRepository.findById(id).orElseThrow(() -> new CustomException("Child not found"));
        if (childDto.getFirstName() != null && !childDto.getFirstName().isEmpty() && !childDto.getFirstName()
                .equals(child.getFirstName())) {
            child.setFirstName(childDto.getFirstName());
        }
        if (childDto.getLastName() != null && !childDto.getLastName().isEmpty() && !childDto.getLastName()
                .equals(child.getLastName())) {
            child.setLastName(childDto.getLastName());
        }

    }

    @Override
    public void deleteChild(Integer id) throws CustomException {
        childRepository.findById(id).orElseThrow(()->new CustomException("Child not found"));
        childRepository.deleteById(id);
    }
}
