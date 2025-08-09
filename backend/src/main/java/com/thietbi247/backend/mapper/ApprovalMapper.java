package com.thietbi247.backend.mapper;

import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.dto.request.ApprovalUpdateRequest;
import com.thietbi247.backend.dto.responsitory.ApprovalResponse;
import com.thietbi247.backend.entity.Approval;

public interface ApprovalMapper {
    void updateApproval(Approval approval, ApprovalUpdateRequest request);
    ApprovalResponse toApprovalResponse(Approval approval);
    ApprovalResponse toApprovalSimpleResponse(Approval approval);
    ApprovalType detectApprovalType(Approval approval);
}