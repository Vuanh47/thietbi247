package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.RequestBorrow;
import com.thietbi247.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RequestBorrowRepository extends JpaRepository<RequestBorrow, String> {
    Optional<RequestBorrow> findByUser_Id(String name);

    Optional<RequestBorrow> findByUser(User user);

    List<RequestBorrow> findAllByUser(User user);
}
