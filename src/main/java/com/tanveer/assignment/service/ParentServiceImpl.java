package com.tanveer.assignment.service;

import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.repository.ParentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public Parent getParentById(Integer id) throws CustomException {
        return parentRepository.findById(id).orElseThrow(() -> new CustomException("Parent not found"));
    }

    @Override
    public Parent saveParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    @Transactional
    public Parent updateParent(Parent parentDto, Integer id) throws CustomException {
        Parent parent = getParentById(id);
        if (parentDto.getFirstName() != null && !parentDto.getFirstName().isEmpty() && !parentDto.getFirstName()
                .equals(parent.getFirstName())) {
            parent.setFirstName(parentDto.getFirstName());
        }
        if (parentDto.getLastName() != null && !parentDto.getLastName().isEmpty() && !parentDto.getLastName()
                .equals(parent.getLastName())) {
            parent.setLastName(parentDto.getLastName());
        }
        if (parentDto.getStreet() != null && !parentDto.getStreet().isEmpty() && !parentDto.getStreet()
                .equals(parent.getStreet())) {
            parent.setStreet(parentDto.getStreet());
        }
        if (parentDto.getCity() != null && !parentDto.getCity().isEmpty() && !parentDto.getCity()
                .equals(parent.getCity())) {
            parent.setCity(parentDto.getCity());
        }
        if (parentDto.getZip() != null && !parentDto.getZip().isEmpty() && !parentDto.getZip()
                .equals(parent.getZip())) {
            parent.setZip(parentDto.getZip());
        }
        if (parentDto.getState() != null && !parentDto.getState().isEmpty() && !parentDto.getState()
                .equals(parent.getState())) {
            parent.setState(parentDto.getState());
        }
        return parent;
    }

    @Override
    public void deleteParent(Integer id) throws CustomException {
        getParentById(id);
        parentRepository.deleteById(id);

    }
}
