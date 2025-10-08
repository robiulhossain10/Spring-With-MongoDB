//package com.oauth2.springwithmongodb.util;
//
//
//import com.oauth2.springwithmongodb.entity.User;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.ArrayList;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponse> handleDataNotFoundException(NotFoundException ex) {
//        ErrorResponse<User> errorResponse = new ErrorResponse();
//        errorResponse.setStatus_code(HttpStatus.NOT_FOUND.value());
//        errorResponse.setStatus("Failed");
//        errorResponse.setReason(ex.getMessage());
//        errorResponse.setData(new ArrayList<>());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(errorResponse);
//    }
//
//
//
//}
