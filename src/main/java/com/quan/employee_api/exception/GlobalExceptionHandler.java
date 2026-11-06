package com.quan.employee_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Provides centralized exception handling for REST API requests.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles validation errors when request data is invalid.
     *
     * @param ex the validation exception
     * @return a response containing validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
                
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiErrorResponse body = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);

        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Handles exceptions when an employee cannot be found.
     *
     * @param ex the exception indicating that the employee was not found
     * @return a response containing employee not found details
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        
        ApiErrorResponse body = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidArgument(IllegalArgumentException ex) {

        ApiErrorResponse body = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return ResponseEntity.badRequest().body(body);
    }
    
    /**
     * Handles unexpected exceptions that are not covered by specific handlers.
     *
     * @param ex the unexpected exception
     * @return a response containing a generic internal server error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleOtherException(Exception ex) {
        
        ApiErrorResponse body = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", null);
        logger.error("Unexpected server error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}