package com.quan.employee_api;

import java.util.Map;

/**
 * Represents a standardized error response returned by the REST API.
 */
public class ApiErrorResponse {
    private int status;
    private String message;
    private Map<String, String> errors;

    /**
     * Creates an API error response.
     *
     * @param status the HTTP status code
     * @param message the error message
     * @param errors the validation errors, if any
     */
    public ApiErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }   
}