package com.thietbi247.backend.service;

import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.entity.RequestBorrow;
import com.thietbi247.backend.mapper.RequestBorrowMapper;
import com.thietbi247.backend.repository.RequestBorrowRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestBorrowService {
    RequestBorrowMapper mapper;
    RequestBorrowRepository repository;
    public RequestBorrowResponse createRequestBorrow(RequestBorowRequest request) {
        RequestBorrow borrow = mapper.toRequestBorrow(request);
        borrow.setBorrowDate(LocalDateTime.now());
        repository.save(borrow);
        return mapper.toRequestBorrowResponse(borrow);
    }
}
