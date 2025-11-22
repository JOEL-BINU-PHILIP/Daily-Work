package com.example.webflux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<Object>> handleServerWebInputException(ServerWebInputException ex) {
        // Log the full exception for diagnostics
        log.error("ServerWebInputException: binding failed or request body invalid", ex);
        return Mono.just(ResponseEntity.badRequest().body(
            new ErrorResponse("Invalid request body", ex.getReason())
        ));
    }

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<Object>> handleAny(Throwable ex) {
        log.error("Unhandled exception in web layer", ex);
        return Mono.just(ResponseEntity.status(500).body(
            new ErrorResponse("Internal server error", ex.getMessage())
        ));
    }

    static class ErrorResponse {
        public String error;
        public String detail;
        public ErrorResponse(String error, String detail) {
            this.error = error;
            this.detail = detail;
        }
    }
}
