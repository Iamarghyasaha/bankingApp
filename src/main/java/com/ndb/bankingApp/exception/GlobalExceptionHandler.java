package com.ndb.bankingApp.exception;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    // used logger
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest rq){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                rq.getDescription(false)
        );
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex,WebRequest rq){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Account Id must be a valid number",
                rq.getDescription(false)
        );
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<CustomErrorResponse> handleNumberFormatException (NumberFormatException ex,WebRequest rq){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Account Id must be a valid number",
                rq.getDescription(false)
        );
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException (Exception ex,WebRequest rq){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                rq.getDescription(false)
        );
        // Consider logging the exception here
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


// Exception handling can be better with DRY principle + using logger


//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(AccountNotFoundException.class)
//    public ResponseEntity<CustomErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest rq){
//        CustomErrorResponse customErrorResponse = createCustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), rq);
//        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<CustomErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest rq){
//        CustomErrorResponse customErrorResponse = createCustomErrorResponse(HttpStatus.BAD_REQUEST, "Account Id must be a valid number", rq);
//        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<CustomErrorResponse> handleNumberFormatException(NumberFormatException ex, WebRequest rq){
//        CustomErrorResponse customErrorResponse = createCustomErrorResponse(HttpStatus.BAD_REQUEST, "Account Id must be a valid number", rq);
//        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex, WebRequest rq){
//        logger.error("An unexpected error occurred", ex);
//        CustomErrorResponse customErrorResponse = createCustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage(), rq);
//        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private CustomErrorResponse createCustomErrorResponse(HttpStatus status, String message, WebRequest rq) {
//        return new CustomErrorResponse(
//                status.value(),
//                LocalDateTime.now(),
//                message,
//                rq.getDescription(false)
//        );
//    }
//}
