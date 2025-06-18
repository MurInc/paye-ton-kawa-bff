package com.BFF_paye_ton_kawa.exceptions;

import com.BFF_paye_ton_kawa.exceptions.schema.ApiError;
import com.BFF_paye_ton_kawa.exceptions.schema.ErrorList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiError> handleHttpRequestError(HttpClientErrorException e) {
        ApiError error = new ApiError();
        error.setStatus(e.getStatusCode());
        error.setMessage(e.getResponseBodyAsString());
        return ResponseEntity.status(e.getStatusCode())
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception e) {
        ApiError error = new ApiError();

        error.setStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(e.getMessage());
        return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e) {
        ApiError error = new ApiError();
        ErrorList errorList = new ErrorList();
        errorList.setErrors(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    ApiError fieldErrorObj = new ApiError();
                    fieldErrorObj.setStatus(HttpStatus.BAD_REQUEST);
                    fieldErrorObj.setMessage(fieldError.getDefaultMessage());
                    return fieldErrorObj;
                }).toList());
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("Invalid input: " + e.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}
