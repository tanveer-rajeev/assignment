package com.tanveer.assignment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMessage.append(message);
        });

        return ResponseEntity.badRequest().body(ApiResponse.builder()
            .success(false)
            .message(errorMessage.toString()).build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(CustomException ex) {
        return ResponseEntity.badRequest()
            .body(ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build());
    }
}
