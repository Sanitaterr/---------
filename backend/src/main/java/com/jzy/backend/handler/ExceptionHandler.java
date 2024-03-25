package com.jzy.backend.handler;

import com.jzy.backend.VO.ResponseResult;
import com.jzy.backend.exceptions.LoginError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseResult handle(Exception exception) {
        log.error(exception.getMessage(),exception);
        return new ResponseResult(HttpStatus.EXPECTATION_FAILED.value(), exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(LoginError.class)
    public ResponseResult handle(LoginError exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseResult(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value(), exception.getMessage());
    }

}
