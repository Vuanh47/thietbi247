package com.thietbi247.backend.mapper;

import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.dto.responsitory.NotificationResponse;
import com.thietbi247.backend.entity.Approval;
import com.thietbi247.backend.entity.Notification;

public interface NotificationMapper {
    Notification fromApproval(Approval approval);
    ApprovalType detectApprovalType(Approval approval);
    String generateContent(Approval approval, ApprovalType type);
    NotificationResponse toResponseNotificationResponse(Notification notification);
}
