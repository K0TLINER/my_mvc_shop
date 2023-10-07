package kr.co.devcs.shop.controller.advice;

import kr.co.devcs.shop.common.dto.ErrorCode;
import kr.co.devcs.shop.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    public RestControllerExceptionHandler() {
        System.out.println("########## RestControllerExceptionHandler Default Constructor Call...");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> httpIllegalArgumentExceptionHandler(IllegalArgumentException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        final ErrorResponse response = new ErrorResponse(errorCode.getDescription(), errorCode.getStatus(), errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "JSON이 전달되지 않았습니다.");
        final ErrorResponse response = new ErrorResponse(errorCode.getDescription(), errorCode.getStatus(), errors);
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        Map<String, String> errors = new HashMap<>();
        Stream.of(exception.getBindingResult().getFieldError()).forEach(fieldError ->
            errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        final ErrorResponse response = new ErrorResponse(errorCode.getDescription(), errorCode.getStatus(), errors);
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> noSuchElementExceptionHandler(NoSuchElementException exception) {
        ErrorCode errorCode = ErrorCode.NO_RETURN_DB_ERROR;
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "비정상적인 접근입니다.");
        final ErrorResponse response = new ErrorResponse(errorCode.getDescription(), errorCode.getStatus(), errors);
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        System.out.println(ex.getClass());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
