package com.Sprints.BankManagementSystem.Configration;

import com.Sprints.BankManagementSystem.exception.ConflictException;
import com.Sprints.BankManagementSystem.exception.CustomErrorResponse;
import com.Sprints.BankManagementSystem.exception.DataNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> HandleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String>  errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() +": " + err.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> HandleConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> HandleNotFoundException(DataNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Error-Code", "DATA_NOT_FOUND");
        headers.add("X-Timestamp", java.time.ZonedDateTime.now().toString());

        CustomErrorResponse body = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                java.time.ZonedDateTime.now().toString()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> HandleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> HandleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
