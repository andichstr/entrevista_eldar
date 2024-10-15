package com.example.demo.controller;

import com.example.demo.dto.ErrorResponseDTO;
import com.example.demo.exceptions.AmountOutOfBoundsException;
import com.example.demo.exceptions.BrandNotValidException;
import com.example.demo.exceptions.CustomException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AmountOutOfBoundsException.class)
    private ResponseEntity<ErrorResponseDTO> buildResponseEntity(AmountOutOfBoundsException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BrandNotValidException.class)
    private ResponseEntity<ErrorResponseDTO> buildResponseEntity(BrandNotValidException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
