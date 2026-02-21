package com.beeshop.sd44.service.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends Exception{
//    @ExceptionHandler(IdInvalidException.class)
//    public GlobalException(String message) {
//        super(message);
//    }
}
