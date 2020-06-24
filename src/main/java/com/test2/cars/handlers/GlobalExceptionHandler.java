package com.test2.cars.handlers;

import com.test2.cars.exceptions.UploadProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UploadProcessingException.class)
    public ResponseEntity<Object> handleUploadFailed(UploadProcessingException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", exception.getMessage());
        body.put("filename", exception.getUploadedFile().getFileName());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
