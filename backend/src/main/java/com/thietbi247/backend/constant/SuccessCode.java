package com.thietbi247.backend.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public enum SuccessCode {
    DEVICE_CREATED(1000, "Device created successfully", HttpStatus.CREATED),
    REQUEST_BORROW_CREATED(1000, "Request borrow created successfully", HttpStatus.CREATED),
    CATEGORY_CREATED(1000, "Category created successfully", HttpStatus.CREATED),
    EMPLOYEE_CREATED(1000, "Employee created successfully", HttpStatus.CREATED),

    GET_ALL_EMPLOYEES(1000, "Employee Get all successfully", HttpStatus.OK),
    DEVICE_LISTED(1000, "Device listed successfully", HttpStatus.OK),
    CATEGORY_LISTED(1000, "Category listed successfully", HttpStatus.OK),

    EMPLOYEE_DELETED_ALL(1000, "Employee deleted all successfully", HttpStatus.OK),
    CATEGORY_DELETED_ALL(1000, "Category deleted all successfully", HttpStatus.OK),
    CATEGORY_DELETED(1000, "Category deleted successfully", HttpStatus.OK),
    EMPLOYEE_DELETED(1000, "Employee deleted successfully", HttpStatus.OK),
    DEVICE_DELETED(1000, "Device deleted successfully", HttpStatus.OK),

    EMPLOYEE_UPDATED(1000, "Employee updated successfully", HttpStatus.OK),
    GET_EMPLOYEE(1000, "Get Employee successfully", HttpStatus.OK),
    GET_CATEGORY_BY_ID(1000, "Get Category by id", HttpStatus.OK),

    LOGIN_SUCCESSFUL(1000, "Login successful", HttpStatus.OK),
    LOGIN_FAIL(1001, "Login fail", HttpStatus.BAD_REQUEST),

    AUTHENTICATED(1002, "Authenticated", HttpStatus.OK);            // Đăng nhập thành công


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
