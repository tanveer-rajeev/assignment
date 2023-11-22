package com.tanveer.assignment.controller;

import com.tanveer.assignment.Exception.ApiResponse;
import com.tanveer.assignment.Exception.CustomException;
import com.tanveer.assignment.model.Parent;
import com.tanveer.assignment.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/parent")
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Parent parent){
        return ResponseEntity.status(201)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Parent created")
                        .data(parentService.saveParent(parent))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody Parent parent) throws CustomException {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Parent updated")
                        .data( parentService.updateParent(parent,id))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Integer id) throws CustomException {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Parent fetched")
                        .data(parentService.getParentById(id))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) throws CustomException {
        parentService.deleteParent(id);
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Parent Deleted")

                        .build());
    }
}
