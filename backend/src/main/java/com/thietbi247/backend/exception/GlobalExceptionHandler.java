package com.thietbi247.backend.exception;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.responsitory.ApiResponse;
import lombok.Builder;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Builder
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ErrorCode error = exception.getError();
        ApiResponse apiResponse = ApiResponse.builder()
                .code(error.getCode())
                .message(error.getMessage())
                .status(error.getStatus())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
        ErrorCode error = ErrorCode.UNAUTHORIZED;
        ApiResponse apiResponse = ApiResponse.builder()
                .code(error.getCode())
                .message(error.getMessage())
                .status(error.getStatus())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String errorKey = exception.getBindingResult().getFieldError().getDefaultMessage();
        ErrorCode errorCode;
        try {
            errorCode = ErrorCode.valueOf(errorKey);//Nếu message thì map sang enum
        } catch (IllegalArgumentException e) {
            errorCode = ErrorCode.INVALID_REQUEST;
        }
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(response);
    }



}
