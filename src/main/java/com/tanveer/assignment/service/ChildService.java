package com.tanveer.assignment.service;

import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.dto.ChildResponseDto;
import com.tanveer.assignment.model.Child;

import java.util.List;

public interface ChildService {
    List<Child> getAllChild();

    ChildResponseDto getChildById(Integer id) throws CustomException;

    Child saveChild(Child user,Integer parentId) throws CustomException;

    void updateChild(Child user,Integer id) throws CustomException;

    void deleteChild(Integer id) throws CustomException;
}
