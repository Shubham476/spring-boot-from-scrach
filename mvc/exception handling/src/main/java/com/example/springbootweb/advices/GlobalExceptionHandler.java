package com.example.springbootweb.advices;

import com.example.springbootweb.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<GlobalCustomExceptionHandler> handleEmployeeNotFoundException(NoSuchElementException exception){
//        GlobalCustomExceptionHandler globalCustomExceptionHandler = GlobalCustomExceptionHandler.builder().
//                status(HttpStatus.NOT_FOUND).
//                message("Resource Not found").
//                build();
//        return new ResponseEntity<>(globalCustomExceptionHandler, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalCustomExceptionHandler> handleEmployeeNotFoundException(ResourceNotFoundException exception){
        GlobalCustomExceptionHandler globalCustomExceptionHandler = GlobalCustomExceptionHandler.builder().
                status(HttpStatus.NOT_FOUND).
                message(exception.getMessage()).
                build();
        return new ResponseEntity<>(globalCustomExceptionHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalCustomExceptionHandler> handleInternalServerError(Exception exception){
        GlobalCustomExceptionHandler globalCustomExceptionHandler = GlobalCustomExceptionHandler.builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                message(exception.getMessage()).
                build();
        return new ResponseEntity<>(globalCustomExceptionHandler, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalCustomExceptionHandler> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());

        GlobalCustomExceptionHandler globalCustomExceptionHandler = GlobalCustomExceptionHandler.builder().
                status(HttpStatus.BAD_REQUEST).
                message("Invalid Request").
                errorList(errors)
                .build();

        return new ResponseEntity<>(globalCustomExceptionHandler, HttpStatus.BAD_REQUEST);
    }
}
