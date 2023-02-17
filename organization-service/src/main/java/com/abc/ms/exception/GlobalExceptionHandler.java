package com.abc.ms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder()
                .timestamp(LocalDateTime.now()).message(exception.getMessage()).description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    // all other exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleOtherException(Exception exception, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder()
                .timestamp(LocalDateTime.now()).message(exception.getMessage()).description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // validation error exception (need to extend ResponseEntityExceptionHandler

}
