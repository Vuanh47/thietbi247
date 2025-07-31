package com.thietbi247.backend.controller;

import com.nimbusds.jose.JOSEException;
import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.request.AuthenticationRequest;
import com.thietbi247.backend.dto.request.IntrospectRequest;
import com.thietbi247.backend.dto.responsitory.AuthenticationResponse;
import com.thietbi247.backend.dto.responsitory.IntrospectResponse;
import com.thietbi247.backend.service.AuthenticationService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService service;
    @PostMapping("/token")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var data = service.authenticate(authenticationRequest);
        return ApiResponseUtil.success(data, SuccessCode.LOGIN_SUCCESSFUL);
    }

    @PostMapping("/introspect")
    public ResponseEntity<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var data = service.introspect(request);
        return ApiResponseUtil.success(data, SuccessCode.LOGIN_SUCCESSFUL);
    }
}
