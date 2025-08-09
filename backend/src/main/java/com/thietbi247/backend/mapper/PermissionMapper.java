package com.thietbi247.backend.mapper;

import com.thietbi247.backend.dto.request.CategoryCreatResquest;
import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.responsitory.CategoryReponse;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.entity.Category;
import com.thietbi247.backend.entity.Permission;

public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

//    void updateDevice(Category employee, DeviceCreatRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
