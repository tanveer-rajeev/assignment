package com.tanveer.assignment.controller;

import com.tanveer.assignment.Exception.ApiResponse;
import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.model.Child;
import com.tanveer.assignment.model.Child;
import com.tanveer.assignment.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/child")
public class ChildController {

    private final ChildService childService;

    @PostMapping("/{parentId}")
    public ResponseEntity<ApiResponse> save(@PathVariable Integer parentId, @RequestBody Child child) throws CustomException {
        return ResponseEntity.status(201)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Child created")
                        .data(childService.saveChild(child,parentId))
                        .build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody Child child) throws CustomException {
        childService.updateChild(child,id);
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Child updated")
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Integer id) throws CustomException {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Child fetched")
                        .data(childService.getChildById(id))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) throws CustomException {
        childService.deleteChild(id);
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Child Deleted")
                        .build());
    }
}
