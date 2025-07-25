package org.example.prj1.exception;

import org.example.prj1.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ErrorResponse> handlingException(RuntimeException ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                "RUNTIME_ERROR"
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldError() != null ?
                ex.getBindingResult().getFieldError().getDefaultMessage() :
                "Validation failed";
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                errorMsg,
                "VALIDATION_ERROR"
        );
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getStatus(),
                ex.getMsg(),
                ex.getCode()
        );
        return ResponseEntity.status(ex.getStatus()).body(response);
    }
}



