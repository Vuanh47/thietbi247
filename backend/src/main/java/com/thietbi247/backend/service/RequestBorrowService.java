package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ApprovalStatus;
import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.entity.*;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.RequestBorrowMapper;
import com.thietbi247.backend.repository.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RequestBorrowService {

    RequestBorrowMapper mapper;
    RequestBorrowRepository repository;
    ApprovalRepository approvalRepository;
    UserRepository userRepository;
    HistoryRepository historyRepository;
    DeviceRepository deviceRepository;

    @PreAuthorize("hasRole('EMPLOYEE')")
    public RequestBorrowResponse createRequestBorrow(RequestBorowRequest request) {
        var info = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(info.getName())
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        if (request.getDevice_ids() == null || request.getDevice_ids().isEmpty()) {
            throw new AppException(ErrorCode.ROLES_REQUIRED);
        }

        Set<Device> devices = new HashSet<>(deviceRepository.findAllById(request.getDevice_ids()));
        if (devices.size() != request.getDevice_ids().size()) {
            throw new AppException(ErrorCode.DEVICE_NOT_EXISTS);
        }

        RequestBorrow borrow = mapper.toRequestBorrow(request);
        borrow.setUser(user);
        borrow.setBorrowDate(LocalDateTime.now());
        borrow = repository.save(borrow);

        Device firstDevice = devices.iterator().next(); // 🔹 lấy thiết bị đầu tiên để gán vào Approval

        Approval approval = Approval.builder()
                .status(ApprovalStatus.PENDING)
                .requestDate(LocalDateTime.now())
                .user(user)
                .type(ApprovalType.REQUEST_BORROW)
                .requestBorrow(borrow)
                .device(firstDevice) // 🔹 gán thiết bị vào Approval
                .build();
        approval = approvalRepository.save(approval);

        List<History> histories = new ArrayList<>();
        for (Device device : devices) {
            if (device.getRequestBorrows() == null) {
                device.setRequestBorrows(new HashSet<>());
            }
            device.getRequestBorrows().add(borrow);

            History history = History.builder()
                    .borrowDate(LocalDateTime.now())
                    .user(user)
                    .device(device)
                    .approval(approval)
                    .build();
            histories.add(history);
        }
        historyRepository.saveAll(histories);

        for (History history : histories) {
            Device device = history.getDevice();
            if (device.getHistoryList() == null)
                device.setHistoryList(new ArrayList<>());
            device.getHistoryList().add(history);
        }

        deviceRepository.saveAll(devices);
        borrow.setDevices(devices);
        repository.save(borrow);

        return mapper.toRequestBorrowResponse(borrow);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public List<RequestBorrowResponse> getAllRequestBorrow() {
        List<RequestBorrow> requestBorrows = repository.findAll();
        return requestBorrows.stream().map(mapper::toRequestBorrowResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public void deleteRequestBorrow(String requestBorrowId) {
        RequestBorrow requestBorrow = repository.findById(requestBorrowId)
                .orElseThrow(() -> new AppException(ErrorCode.REQUEST_BORROW_NOT_EXISTS));
        repository.delete(requestBorrow);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAll(){
        List<Approval> approvals = approvalRepository.findAll();
        for (Approval approval : approvals) {
            approval.setRequestBorrow(null);
        }
        approvalRepository.saveAll(approvals);
        repository.deleteAll();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<RequestBorrowResponse> getInfo(){
        var info = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(info.getName()).orElseThrow(() ->
                new AppException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        List<RequestBorrow> requestBorrows = repository.findAllByUser(user);
        if (requestBorrows.isEmpty()) {
            throw new AppException(ErrorCode.REQUEST_BORROW_NOT_EXISTS);
        }

        return requestBorrows.stream().map(mapper::toRequestBorrowResponse).collect(Collectors.toList());
    }
}
