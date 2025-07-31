package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.User;
import com.thietbi247.backend.entity.RequestBorrow;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.RequestBorrowMapper;
import com.thietbi247.backend.repository.DeviceRepository;
import com.thietbi247.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestBorrowMapperImpl implements RequestBorrowMapper {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Override
    public RequestBorrow toRequestBorrow(RequestBorowRequest request) {
        if (request == null) {
            return null;
        }
        log.info(request.toString());
        Device device = null;
        User user = null;

        if (request.getDevice_id() != null) {
            device = deviceRepository.findById(request.getDevice_id())
                    .orElseThrow(() -> new AppException(ErrorCode.DEVICE_NOT_EXISTS));
        }

        if (request.getEmployee_id() != null) {
            user = userRepository.findById(request.getEmployee_id())
                    .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTS));
        }

        return RequestBorrow.builder()
                .id(request.getId())
                .borrowDate(request.getBorrowDate())
                .borrowReason(request.getBorrowReason())
                .dueDate(request.getDueDate())
                .device(device)
                .user(user)
                .build();
    }

    @Override
    public void updateDevice(Device employee, DeviceCreatRequest request) {

    }

    @Override
    public RequestBorrowResponse toRequestBorrowResponse(RequestBorrow requestBorrow) {
        return RequestBorrowResponse.builder()
                .id(requestBorrow.getId())
                .borrowDate(requestBorrow.getBorrowDate())
                .borrowReason(requestBorrow.getBorrowReason())
                .dueDate(requestBorrow.getDueDate())
                .device_id(requestBorrow.getDevice() != null ? requestBorrow.getDevice().getId() : null)
                .employee_id(requestBorrow.getUser() != null ? requestBorrow.getUser().getId() : null)
                .build();
    }
}

