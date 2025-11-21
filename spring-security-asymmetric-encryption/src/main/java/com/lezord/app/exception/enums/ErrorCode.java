package com.lezord.app.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND),
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Change password mismatch", HttpStatus.BAD_REQUEST),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD", "Invalid current password", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED", "Account already deactivated", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_ACTIVATED("ACCOUNT_ALREADY_ACTIVATED", "Account already activated", HttpStatus.BAD_REQUEST),

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
