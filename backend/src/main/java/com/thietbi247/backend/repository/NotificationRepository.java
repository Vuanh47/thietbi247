package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Approval;
import com.thietbi247.backend.entity.Notification;
import com.thietbi247.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    Optional<Notification> findByUser_Id(String id);

    List<Notification> findAllByUser(User user);
}
