package com.thietbi247.backend.constant;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPLOYEE_EXISTS(1001, "Employee already exists", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTS(1001, "Category not exists", HttpStatus.BAD_REQUEST),
    DEVICE_NOT_EXISTS(1001, "Device not exists", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_EXISTS(1005, "Employee not exists", HttpStatus.NOT_FOUND),

    EMPLOYEE_VALIDATE_EXCEPTION(1002, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT(1003, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    EMPLOYEE_MUST_BE_ADULT(1004, "Employee must be at least 18 years old", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1006, "Invalid message key", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Invalid password", HttpStatus.UNAUTHORIZED),   // Sai mật khẩu

    LOGIN_FAIL(1001, "Login fail", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED), // Chưa đăng nhập
    UNAUTHORIZED(1003, "You don't have permission", HttpStatus.FORBIDDEN);// Không đủ quyền


    int code;
    String message;
    HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
