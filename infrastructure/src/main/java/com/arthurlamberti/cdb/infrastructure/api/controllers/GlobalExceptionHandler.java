package com.arthurlamberti.cdb.infrastructure.api.controllers;

import com.arthurlamberti.cdb.exceptions.DomainException;
import com.arthurlamberti.cdb.exceptions.NotFoundException;
import com.arthurlamberti.cdb.validation.Error;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }

    @ExceptionHandler(value = FeignException.UnprocessableEntity.class)
    public ResponseEntity<?> handleUnprocessableEntity(final FeignException.UnprocessableEntity ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex.getMessage()));
    }

    record ApiError(String message, List<Error> errors) {
        static ApiError from(final DomainException ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }

        static ApiError from(final String message){
            return new ApiError("Failed to process entity", List.of(new Error(message)));
        }
    }
}
