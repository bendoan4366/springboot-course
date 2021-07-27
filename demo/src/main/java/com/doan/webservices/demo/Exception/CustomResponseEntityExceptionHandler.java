package com.doan.webservices.demo.Exception;

import com.doan.webservices.demo.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

//extend Spring Response entity handler to allow for custom response messages
@ControllerAdvice //allows this controller shared across multiple @controller classes
@RestController

//used if you want to override the standard Entity reponse handler
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // handle all exceptions - to be overwritten later
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        //instantiate exception response to be built into response entity
        ExceptionResponse eResponse = new ExceptionResponse(new Timestamp(System.currentTimeMillis()), ex.getMessage(), request.getDescription(false));

        //create and return new response entity in one step - will return only 500 internal server errors
        return new ResponseEntity(eResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) // handle UserNotFoundException
    public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request){
        //instantiate exception response to be built into response entity
        ExceptionResponse eResponse = new ExceptionResponse(new Timestamp(System.currentTimeMillis()), ex.getMessage(), request.getDescription(false));

        //create and return new response entity in one step - will return only 500 internal server errors
        return new ResponseEntity(eResponse, HttpStatus.NOT_FOUND);
    }

    //override
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //create new exception response and extract BindingResponse to display validation failure
        ExceptionResponse eResponse = new ExceptionResponse(new Timestamp(System.currentTimeMillis()), "Data validation failed", ex.getBindingResult().toString());

        //create and return new response entity in one step - will return Bad request
        return new ResponseEntity(eResponse, HttpStatus.BAD_REQUEST);

    }
}
