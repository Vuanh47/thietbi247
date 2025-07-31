package com.thietbi247.backend.controller;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.service.RequestBorrowService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/requestBorrow")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestBorrowController {
    RequestBorrowService service;

    @PostMapping
    public ResponseEntity<RequestBorrowResponse> createDevice(@RequestBody RequestBorowRequest request) {
        log.info(request.toString());
        RequestBorrowResponse data = service.createRequestBorrow(request);
        log.info(data.toString());
        return ApiResponseUtil.success(data, SuccessCode.REQUEST_BORROW_CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<DeviceResponse>> getAllDevices() {
//        List<DeviceResponse> data = deviceService.getAllDevices();
//        return ApiResponseUtil.success(data, ApiCode.DEVICE_LISTED);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<ApiResponse<Void>> deleteDevice(@PathVariable String id) {
//        deviceService.deleteDevice(id);
//        return ApiResponseUtil.success(ApiCode.DEVICE_DELETED);
//    }


}