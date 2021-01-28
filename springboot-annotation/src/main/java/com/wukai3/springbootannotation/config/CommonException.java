package com.wukai3.springbootannotation.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author kaiwu3
 */

@Getter
public class CommonException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CommonException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
