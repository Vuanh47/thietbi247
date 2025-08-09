package com.thietbi247.backend.service;

import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.request.RoleRequest;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.dto.responsitory.RoleResponse;
import com.thietbi247.backend.entity.Permission;
import com.thietbi247.backend.entity.Role;
import com.thietbi247.backend.mapper.PermissionMapper;
import com.thietbi247.backend.mapper.RoleMapper;
import com.thietbi247.backend.repository.PermissionRepository;
import com.thietbi247.backend.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository repository;
    PermissionRepository permissionRepository;
    RoleMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse createRole(RoleRequest request) {
        Role role = mapper.toRole(request);
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(request.getPermission()));
        role.setPermissions(permissions);
        repository.save(role);
        return mapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
     public List<RoleResponse> getAllRole(){
        var role = repository.findAll();
        return role.stream().map(mapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public  void deleteRole(String role){

        repository.deleteByName(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllRole() {
        repository.deleteAll();
    }
}
