package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ApprovalStatus;
import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.ErrorReportRequest;
import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.responsitory.ErrorReportResponse;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.entity.*;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.ErrorReportMapper;
import com.thietbi247.backend.mapper.RequestBorrowMapper;
import com.thietbi247.backend.repository.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorReportService {
    ErrorReportMapper mapper;
    ErrorReportRepository repository;
    UserRepository userRepository;
    ApprovalRepository approvalRepository;
    HistoryRepository historyRepository;
    DeviceRepository deviceRepository;

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ErrorReportResponse createErrorReport(ErrorReportRequest request) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName())
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        if (request.getDevice_ids() == null || request.getDevice_ids().isEmpty()) {
            throw new AppException(ErrorCode.ROLES_REQUIRED);
        }

        Set<Device> devices = new HashSet<>(deviceRepository.findAllById(request.getDevice_ids()));
        if (devices.size() != request.getDevice_ids().size()) {
            throw new AppException(ErrorCode.DEVICE_NOT_EXISTS);
        }

        ErrorReport errorReport = mapper.toErrorReport(request);
        errorReport.setUser(user);
        errorReport = repository.save(errorReport);

        Device firstDevice = devices.iterator().next(); // 🔹 Lấy thiết bị đầu tiên để gán vào Approval

        Approval approval = Approval.builder()
                .status(ApprovalStatus.PENDING)
                .requestDate(LocalDateTime.now())
                .user(user)
                .type(ApprovalType.ERROR_REPORT)
                .errorReport(errorReport)
                .device(firstDevice) // 🔹 Gán thiết bị vào Approval
                .build();
        approval = approvalRepository.save(approval);

        List<History> histories = new ArrayList<>();
        for (Device device : devices) {
            if (device.getErrorReports() == null)
                device.setErrorReports(new HashSet<>());
            device.getErrorReports().add(errorReport);

            History history = History.builder()
                    .borrowDate(LocalDateTime.now())
                    .user(user)
                    .device(device)
                    .approval(approval)
                    .build();
            histories.add(history);
        }

        historyRepository.saveAll(histories);

        for (History h : histories) {
            Device d = h.getDevice();
            if (d.getHistoryList() == null) d.setHistoryList(new ArrayList<>());
            d.getHistoryList().add(h);
        }
        deviceRepository.saveAll(devices);

        errorReport.setDevices(devices);
        repository.save(errorReport);

        return mapper.toErrorReportResponse(errorReport);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public List<ErrorReportResponse> getAllErrorReport() {
        List<ErrorReport>  errorReports = repository.findAll();
        return errorReports.stream().map(mapper::toErrorReportResponse).collect(Collectors.toList());
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAll() {
        List<Approval> approvals = approvalRepository.findAllByType(ApprovalType.ERROR_REPORT);
        List<History> histories = historyRepository.findAllByApprovalIn(approvals);
        historyRepository.deleteAll(histories);
        approvalRepository.deleteAll(approvals);
        repository.deleteAll();
    }


//    public void delete(String id){
//        ErrorReport errorReport =  repository.findById(id).orElseThrow(()
//                -> new AppException(ErrorCode.ERROR_REPORT_NOT_EXISTS));
//        repository.delete(errorReport);
//    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<ErrorReportResponse> getInfo(){
        var info = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(info.getName()).orElseThrow(() ->
                new AppException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        List<ErrorReport> errorReports = repository.findAllByUser(user);
        if (errorReports.isEmpty()) {
            throw new AppException(ErrorCode.ERROR_REPORT_NOT_EXISTS);
        }
        return errorReports.stream().map(mapper::toErrorReportResponse).collect(Collectors.toList());
    }
}
