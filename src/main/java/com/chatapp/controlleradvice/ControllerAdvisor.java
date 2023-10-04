package com.chatapp.controlleradvice;

import com.chatapp.commond.ResponseData;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandling(RuntimeException e) {
        ResponseData<ErrorModel> responseData = new ResponseData<>(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<?> duplicateUsernameExceptionHandling(DuplicateUsernameException e) {
        ErrorModel messageModel = new ErrorModel(e.getMessage());
        return new ResponseEntity<>(messageModel, HttpStatus.BAD_REQUEST);
    }
}
