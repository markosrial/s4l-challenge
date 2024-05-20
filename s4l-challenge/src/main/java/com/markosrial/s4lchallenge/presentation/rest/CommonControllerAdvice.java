package com.markosrial.s4lchallenge.presentation.rest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.openapitools.model.ErrorResponse;
import org.openapitools.model.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice for common exceptions
 */
@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        ValidationErrorResponse response = new ValidationErrorResponse();

        FieldError fieldError = exception.getBindingResult().getFieldErrors().stream().findFirst().get();
        response.setMessage(String.format("Error! %s", fieldError.getDefaultMessage()));

        return response;

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {

        ConstraintViolation<?> fieldError = exception.getConstraintViolations().stream().findFirst().get();

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setMessage(String.format("Error! Field %s %s", fieldError.getPropertyPath(), fieldError.getMessage()));

        return response;

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleRuntimeException(RuntimeException exception) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return response;

    }

}
