package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.RequestBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestBorrowRepository extends JpaRepository<RequestBorrow, String> {
}
