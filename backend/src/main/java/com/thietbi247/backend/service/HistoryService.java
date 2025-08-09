package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.responsitory.HistoryResponse;
import com.thietbi247.backend.entity.History;
import com.thietbi247.backend.entity.User;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.HistoryMapper;
import com.thietbi247.backend.repository.HistoryRepository;
import com.thietbi247.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Builder
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HistoryService {
    HistoryMapper mapper;
    UserRepository userRepository;
    HistoryRepository historyRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryResponse> getAll(){
        List<History> histories = historyRepository.findAll();
        return histories.stream().map(mapper::toHistoryResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<HistoryResponse> getInfo(){
        var info = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(info.getName()).orElseThrow(() ->
                new AppException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        List<History> histories = historyRepository.findAllByUser(user);
        if (histories.isEmpty()) {
            throw new AppException(ErrorCode.HISTORY_NOT_EXISTS);
        }

        return histories.stream().map(mapper::toHistoryResponse).collect(Collectors.toList());
    }
}
