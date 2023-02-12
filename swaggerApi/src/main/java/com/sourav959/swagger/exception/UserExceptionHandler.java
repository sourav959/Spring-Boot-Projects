package com.sourav959.swagger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is exception handler class.
 *
 * @author sourav959
 */
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(IllegalArgumentException illegalArgumentException) {
        var errorResponse = ErrorResponse.builder()
                .message(illegalArgumentException.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGenericExceptions(RuntimeException runtimeException) {
        var errorResponse = ErrorResponse.builder()
                .message(runtimeException.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGenericExceptions(Exception exception) {
        var errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
