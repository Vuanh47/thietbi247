package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.ErrorReport;
import com.thietbi247.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ErrorReportRepository extends JpaRepository<ErrorReport, String> {

    Optional<ErrorReport> findByUser_Id(String name);

    Optional<ErrorReport> findByUser(User user);

    List<ErrorReport> findAllByUser(User user);
}
