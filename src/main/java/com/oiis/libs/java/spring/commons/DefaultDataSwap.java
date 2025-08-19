package com.oiis.libs.java.spring.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class DefaultDataSwap {

    public boolean success = false;
    public Object data = null;
    public String message = null;
    public HttpStatus httpStatus = null;
    public Exception exception = null;

    public DefaultDataSwap() {}

    public DefaultDataSwap(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setException(Exception exception) {
        this.httpStatus = Objects.requireNonNullElse(this.httpStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        this.exception = exception;
        if (!StringUtils.hasText(this.message) && this.exception != null) {
            this.message = this.exception.getMessage();
        }
    }

    public ResponseEntity<DefaultDataSwap> sendHttpResponse() {
        return this.success ? ResponseEntity.status(HttpStatus.OK).body(this) : ResponseEntity.status(this.httpStatus != null ? this.httpStatus : HttpStatus.INTERNAL_SERVER_ERROR).body(this);
    }
}
