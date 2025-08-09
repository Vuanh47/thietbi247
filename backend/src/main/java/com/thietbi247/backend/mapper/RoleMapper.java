package com.thietbi247.backend.mapper;

import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.request.RoleRequest;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.dto.responsitory.RoleResponse;
import com.thietbi247.backend.entity.Permission;
import com.thietbi247.backend.entity.Role;

public interface RoleMapper {
    Role toRole(RoleRequest request);

//    void updateDevice(Category employee, DeviceCreatRequest request);
    RoleResponse toRoleResponse(Role role);
}
