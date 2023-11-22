package com.tanveer.assignment.repository;

import com.tanveer.assignment.model.Child;
import com.tanveer.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child,Integer> {
}
