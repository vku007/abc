package vku.test.service.c.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vku.test.service.c.dto.ErrorEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc


// so, in this example we have two handlers: for IO an general exceptions, which return same entry, but different statuses
@ControllerAdvice
public class SimpleExceptionHandler {
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorEntity> handleIO (HttpServletRequest req, Exception e) {
        ErrorEntity entry = new ErrorEntity();
        entry.setMessage("Error happens: " + e.getMessage());
        entry.setErrorCode(6666);
        ResponseEntity<ErrorEntity> result = new ResponseEntity(entry, HttpStatus.INTERNAL_SERVER_ERROR);
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorEntity> handleConstraintViolationException (HttpServletRequest req, Exception e) {
        ErrorEntity entry = new ErrorEntity();

        entry.setMessage("Request Validation Error happens: " + e.getMessage());
        entry.setErrorCode(776);
        ResponseEntity<ErrorEntity> result = new ResponseEntity(entry, HttpStatus.BAD_REQUEST);
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> handleBeanValidationException (HttpServletRequest req, MethodArgumentNotValidException e) {
        ErrorEntity entry = new ErrorEntity();
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + " " + x.getDefaultMessage())
                .collect(Collectors.toList());

        entry.setMessage("Request Validation Error happens: " + errors);
        entry.setErrorCode(777);
        ResponseEntity<ErrorEntity> result = new ResponseEntity(entry, HttpStatus.BAD_REQUEST);
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorEntity> handle (HttpServletRequest req, Exception e) {
        ErrorEntity entry = new ErrorEntity();
        entry.setMessage("Error happens: " + e.getMessage());
        entry.setErrorCode(666);
        ResponseEntity<ErrorEntity> result = new ResponseEntity(entry, HttpStatus.BAD_REQUEST);
        e.printStackTrace();
        return result;
    }
}
