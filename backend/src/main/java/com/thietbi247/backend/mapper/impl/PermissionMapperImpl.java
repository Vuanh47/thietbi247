package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.entity.Permission;
import com.thietbi247.backend.mapper.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapperImpl implements PermissionMapper {


    @Override
    public Permission toPermission(PermissionRequest request) {
        if (request  == null) {
            return null;
        }

        return Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    @Override
    public PermissionResponse toPermissionResponse(Permission permission) {
        if (permission == null) {
            return null;
        }

        return PermissionResponse.builder()
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
