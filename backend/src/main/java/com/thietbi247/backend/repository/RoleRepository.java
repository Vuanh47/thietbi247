package com.thietbi247.backend.repository;

import com.thietbi247.backend.dto.request.RoleRequest;
import com.thietbi247.backend.entity.Permission;
import com.thietbi247.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {
    void deleteByName(String role);

    List<Role> findAllByNameIn(List<String> names);

    boolean existsByName(String name);
}
