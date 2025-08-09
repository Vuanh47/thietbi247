package com.thietbi247.backend.dto.responsitory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thietbi247.backend.constant.ApprovalStatus;
import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.entity.Device;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApprovalResponse {
    private String id;
    private ApprovalStatus status;
    private ApprovalType type;
    private LocalDateTime requestDate;
    private LocalDateTime approveDate;
    private String errorReportId;
    private String returnDeviceId;
    private String requestBorrowId;
    private UserResponse user;
    private DeviceResponse device;

}
