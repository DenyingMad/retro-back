package com.example.retro.adapter.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn(e.getMessage());
    }
}
