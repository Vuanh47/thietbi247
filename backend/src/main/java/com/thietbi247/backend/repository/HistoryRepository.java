package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Approval;
import com.thietbi247.backend.entity.History;
import com.thietbi247.backend.entity.User;
import com.thietbi247.backend.service.ApprovalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, String> {
    Optional<History> findByApproval(Approval approval);
     Optional<History> findByUser(User user);
    List<History> findAllByUser(User user);

    List<History> findAllByApprovalIn(List<Approval> approvals);
}
