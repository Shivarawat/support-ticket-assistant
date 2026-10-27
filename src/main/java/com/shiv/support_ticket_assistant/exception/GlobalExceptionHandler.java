package com.shiv.support_ticket_assistant.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.shiv.support_ticket_assistant.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Service couldn't find the id -> 404
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(TicketNotFoundException ex) {
        return new ErrorResponse(404, ex.getMessage(), null);
    }

    // @Valid failed -> 400 with one message per broken field
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        return new ErrorResponse(400, "Validation failed", fieldErrors);
    }

    // Broken JSON, or an unknown enum value like "priority": "SUPER" -> 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUnreadable(HttpMessageNotReadableException ex) {
        return new ErrorResponse(400, "Malformed request body", null);
    }

    // Arguement type mismatch error handler like "/abc" instead of "/213"
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMismatch(MethodArgumentTypeMismatchException ex) {
        return new ErrorResponse(400, "Invalid Id", null);
    }

}
