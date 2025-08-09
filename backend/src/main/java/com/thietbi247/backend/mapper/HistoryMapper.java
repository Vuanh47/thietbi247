package com.thietbi247.backend.mapper;

import com.thietbi247.backend.dto.responsitory.HistoryResponse;
import com.thietbi247.backend.entity.History;

public interface HistoryMapper {
    HistoryResponse toHistoryResponse(History history);
}
