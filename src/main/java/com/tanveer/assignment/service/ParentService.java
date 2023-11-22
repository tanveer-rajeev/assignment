package com.tanveer.assignment.service;

import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.model.Parent;

import java.util.List;

public interface ParentService {
    List<Parent> getAllParents();

    Parent getParentById(Integer id) throws CustomException;

    Parent saveParent(Parent user);

    Parent updateParent(Parent user,Integer id) throws CustomException;

    void deleteParent(Integer id) throws CustomException;
}
