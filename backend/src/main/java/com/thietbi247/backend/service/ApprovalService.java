package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ApprovalStatus;
import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.ApprovalUpdateRequest;
import com.thietbi247.backend.dto.responsitory.ApprovalResponse;
import com.thietbi247.backend.entity.Approval;
import com.thietbi247.backend.entity.ErrorReport;
import com.thietbi247.backend.entity.History;
import com.thietbi247.backend.entity.Notification;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.ApprovalMapper;
import com.thietbi247.backend.mapper.NotificationMapper;
import com.thietbi247.backend.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApprovalService {
    NotificationRepository notificationRepository;
    ErrorReportRepository errorReportRepository;
    ErrorReportService errorReportService;
    ReturnDeviceRepository returnDeviceRepository;
    RequestBorrowRepository requestBorrowRepository;
    NotificationMapper notificationMapper;
    HistoryRepository historyRepository;

    ApprovalRepository repository;
    ApprovalMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<ApprovalResponse> getAll() {
        List<Approval> approvals = repository.findAll();
        return approvals.stream().map(mapper::toApprovalResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ApprovalResponse updateApproval(ApprovalUpdateRequest request) {
        Approval approval = repository.findById(request.getId()).orElseThrow(() ->
                new AppException(ErrorCode.APPROVAL_NOT_EXISTS));
        mapper.updateApproval(approval, request);
        repository.save(approval);

        Notification notification = notificationMapper.fromApproval(approval);
        String content = notificationMapper.generateContent(approval, approval.getType());

        notification.setContent(content);
        notification.setNotificationDate(LocalDateTime.now());
        notificationRepository.save(notification);

        if (approval.getType().equals(ApprovalType.ERROR_REPORT)) {
            errorReportRepository.delete(approval.getErrorReport());

        } else if (approval.getType().equals(ApprovalType.REQUEST_BORROW)) {
            requestBorrowRepository.delete(approval.getRequestBorrow());

        } else if (approval.getType().equals(ApprovalType.RETURN_DEVICE)) {
            returnDeviceRepository.delete(approval.getReturnDevice());
        }

        return mapper.toApprovalResponse(approval);
    }
}
