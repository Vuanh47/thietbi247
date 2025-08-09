package com.thietbi247.backend.dto.request;

import com.thietbi247.backend.constant.ApprovalStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApprovalUpdateRequest {
    private  String id;
    private ApprovalStatus status;
}
