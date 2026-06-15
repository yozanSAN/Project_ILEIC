package com.ProjetILEIC.ILIEC.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", "Not Found",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        // Log the real ugly error internally so you can debug it in your IDE console
        log.error("Internal server error caught: ", ex);

        //Send a completely safe, generic message back to the frontend/client
        return new ResponseEntity<>(
                Map.of(
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", "Internal Server Error",
                        "message", "Something went wrong on our end. Please try again later.",
                        "timestamp", LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}