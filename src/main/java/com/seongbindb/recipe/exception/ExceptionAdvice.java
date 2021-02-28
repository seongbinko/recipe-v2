package com.seongbindb.recipe.exception;

import com.seongbindb.recipe.annotation.CurrentUser;
import com.seongbindb.recipe.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public String handleRuntimeException(@CurrentUser User user, HttpServletRequest req, RuntimeException e) {
        if(user != null) {
            log.info("'{}' requested '{}'", user.getNickname(), req.getRequestURI());
        } else {
            log.info("requested '{}'" , req.getRequestURI());
        }
        log.error("bad request", e);
        return "/error/error";
    }
}
