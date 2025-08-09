package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Permission;
import com.thietbi247.backend.entity.RequestBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
