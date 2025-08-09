package com.thietbi247.backend.mapper.impl;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.ErrorReportRequest;
import com.thietbi247.backend.dto.request.RoleRequest;
import com.thietbi247.backend.dto.responsitory.ErrorReportResponse;
import com.thietbi247.backend.dto.responsitory.RoleResponse;
import com.thietbi247.backend.entity.*;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.ErrorReportMapper;
import com.thietbi247.backend.mapper.PermissionMapper;
import com.thietbi247.backend.mapper.RoleMapper;
import com.thietbi247.backend.repository.DeviceRepository;
import com.thietbi247.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {
    private final PermissionMapper permissionMapper;

    @Override
    public Role toRole(RoleRequest request) {
        if (request == null){
            return null;
        }

        return Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    @Override
    public RoleResponse toRoleResponse(Role role) {
        if (role == null){
            return null;
        }
        return RoleResponse.builder()
                .name(role.getName())
                .description(role.getDescription())
                .permissions(
                        role.getPermissions() != null
                        ? role.getPermissions().stream()
                                .map(permissionMapper::toPermissionResponse)
                                .collect(Collectors.toSet())
                                : Collections.emptySet()
                )
                .build();
    }

}
