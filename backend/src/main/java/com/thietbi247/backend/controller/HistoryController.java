package com.thietbi247.backend.controller;

import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.responsitory.HistoryResponse;
import com.thietbi247.backend.entity.History;
import com.thietbi247.backend.repository.HistoryRepository;
import com.thietbi247.backend.service.HistoryService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/history")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HistoryController {
    HistoryService service;

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getAllHistory() {
        List<HistoryResponse> data = service.getAll();
        return ApiResponseUtil.success(data, SuccessCode.HISTORY_LISTED);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<List<HistoryResponse>> getMyInfo() {
        List<HistoryResponse> data = service.getInfo();
        return ApiResponseUtil.success(data, SuccessCode.GET_HISTORY);
    }

}
