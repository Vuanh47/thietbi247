package com.thietbi247.backend.repository;

import com.thietbi247.backend.constant.ApprovalType;
import com.thietbi247.backend.entity.Approval;
import com.thietbi247.backend.entity.RequestBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, String> {
    List<Approval> findAllByType(ApprovalType approvalType);
}
