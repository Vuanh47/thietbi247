package com.thietbi247.backend.exception;

import com.thietbi247.backend.constant.ErrorCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level =  AccessLevel.PRIVATE)
public class AppException extends RuntimeException{
    ErrorCode error;

    public AppException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorCode getError() {
        return error;
    }
}
