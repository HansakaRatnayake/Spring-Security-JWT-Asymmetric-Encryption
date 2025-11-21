package com.lezord.app.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND),

    ;

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String defaultMessage, HttpStatus httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }
}
