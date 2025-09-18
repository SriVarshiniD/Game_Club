package com.varshini.game_club.Exceptions;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Empty;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
public class GlobalExceptions {
    @ExceptionHandler(IdNotPresentException.class)
    public String handleIdNotPresentException(IdNotPresentException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(BussinessException.class)
    public String handleBussinessException(BussinessException ex) {
        return ex.getMessage();
    }
    
}
   