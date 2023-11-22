package com.tanveer.assignment.repository;

import com.tanveer.assignment.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Integer> {
}
