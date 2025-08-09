package com.thietbi247.backend.controller;

import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.request.ReturnDeviceRequest;
import com.thietbi247.backend.dto.responsitory.ApiResponse;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.dto.responsitory.ReturnDeviceResponse;
import com.thietbi247.backend.service.DeviceService;
import com.thietbi247.backend.service.ReturnDeviceService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/admin/return_device")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReturnDeviceController {
    ReturnDeviceService service;

    @PostMapping
    public ResponseEntity<ReturnDeviceResponse> createReturnDevice(@RequestBody ReturnDeviceRequest request) {
        ReturnDeviceResponse data = service.createReturnDevice(request);
        return  ApiResponseUtil.success(data, SuccessCode.RETURN_DEVICE_CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReturnDeviceResponse>> getAllDevices() {
        List<ReturnDeviceResponse> data = service.getAllReturnDevice();
        return ApiResponseUtil.success(data, SuccessCode.RETURN_DEVICE_LISTED);
    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<ApiResponse<Void>> deleteDevice(@PathVariable String id) {
//        deviceService.deleteDevice(id);
//        return ApiResponseUtil.success(SuccessCode.DEVICE_DELETED);
//    }
//
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAll() {
        service.deleteAll();
        return ApiResponseUtil.success(SuccessCode.RETURN_DEVICE_DELETED_ALL);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<List<ReturnDeviceResponse>> getMyRequestBorrow() {
        List<ReturnDeviceResponse> data = service.getInfo();
        return ApiResponseUtil.success(data, SuccessCode.GET_RETURN_DEVICE);
    }
}