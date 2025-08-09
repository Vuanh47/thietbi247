package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.Permission;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.DeviceMapper;
import com.thietbi247.backend.mapper.PermissionMapper;
import com.thietbi247.backend.repository.DeviceRepository;
import com.thietbi247.backend.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository repository;
    PermissionMapper mapper;

    @PreAuthorize("hasRole('EMPLOYEE')")
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = mapper.toPermission(request);
        repository.save(permission);
        return mapper.toPermissionResponse(permission);
    }

    @PreAuthorize("hasRole('ADMIN')")
     public List<PermissionResponse> getAllPermission(){
        var permission = repository.findAll();
         return permission.stream().map(mapper::toPermissionResponse).toList();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public  void deletePermission(String permission){
        repository.deleteById(permission);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllPermission() {
        repository.deleteAll();
    }
}
