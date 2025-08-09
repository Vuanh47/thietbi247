package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.ApprovalUpdateRequest;
import com.thietbi247.backend.dto.responsitory.ApprovalResponse;
import com.thietbi247.backend.entity.*;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.ApprovalMapper;
import com.thietbi247.backend.mapper.DeviceMapper;
import com.thietbi247.backend.mapper.UserMapper;
import com.thietbi247.backend.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApprovalMapperImpl implements ApprovalMapper {
    DeviceMapper deviceMapper;
    UserMapper userMapper;


    @Override
    public void updateApproval(Approval approval, ApprovalUpdateRequest request) {
        approval.setStatus(request.getStatus());
        approval.setApprovalDate(LocalDateTime.now());
    }

    @Override
    public ApprovalResponse toApprovalResponse(Approval approval) {
        return ApprovalResponse.builder()
                .id(approval.getId())
                .status(approval.getStatus())
                .type(approval.getType())
                .approveDate(approval.getApprovalDate())
                .requestDate(approval.getRequestDate())
                .device(deviceMapper.toDeviceResponse(approval.getDevice()))
                .errorReportId(approval.getErrorReport() != null ? approval.getErrorReport().getId() : null)
                .returnDeviceId(approval.getReturnDevice() != null ? approval.getReturnDevice().getId() : null)
                .requestBorrowId(approval.getRequestBorrow() != null ? approval.getRequestBorrow().getId() : null)
                .user(userMapper.toUserSimpleResponse(approval.getUser()))
                .build();
    }

    @Override
    public ApprovalResponse toApprovalSimpleResponse(Approval approval) {
        return ApprovalResponse.builder()
                .id(approval.getId())
                .status(approval.getStatus())
                .type(approval.getType())
                .approveDate(approval.getApprovalDate())
                .requestDate(approval.getRequestDate())
                .device(deviceMapper.toDeviceResponse(approval.getDevice()))
                .errorReportId(approval.getErrorReport() != null ? approval.getErrorReport().getId() : null)
                .returnDeviceId(approval.getReturnDevice() != null ? approval.getReturnDevice().getId() : null)
                .requestBorrowId(approval.getRequestBorrow() != null ? approval.getRequestBorrow().getId() : null)
                .build();
    }

    public ApprovalType detectApprovalType(Approval approval) {
        if (approval.getErrorReport() != null) {
            return ApprovalType.ERROR_REPORT;
        } else if (approval.getReturnDevice() != null) {
            return ApprovalType.RETURN_DEVICE;
        } else if (approval.getRequestBorrow() != null) {
            return ApprovalType.REQUEST_BORROW;
        } else {
            throw new AppException(ErrorCode.APPROVAL_TYPE_INVALID);
        }
    }


}
