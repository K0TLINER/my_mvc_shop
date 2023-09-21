package kr.co.devcs.shop.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    public RestControllerExceptionHandler() {
        System.out.println("########## RestControllerExceptionHandler Default Constructor Call...");
    }

//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<String> handleBindException(BindException ex) {
//        // BindingResult에 있는 오류를 처리하는 로직을 여기에 작성합니다.
//        StringBuilder errorMessage = new StringBuilder("Validation errors:\n");
//        for (FieldError error : ex.getFieldErrors()) {
//            errorMessage.append(error.getField())
//                    .append(": ")
//                    .append(error.getDefaultMessage())
//                    .append("\n");
//        }
//        return ResponseEntity.badRequest().body(errorMessage.toString());
//    }
    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
}
