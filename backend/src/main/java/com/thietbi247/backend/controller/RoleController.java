package com.thietbi247.backend.controller;

import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.request.RoleRequest;
import com.thietbi247.backend.dto.responsitory.ApiResponse;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.dto.responsitory.RoleResponse;
import com.thietbi247.backend.service.PermissionService;
import com.thietbi247.backend.service.RoleService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService service;

    @PostMapping
    public ResponseEntity<RoleResponse> addRole(@RequestBody RoleRequest resquest) {
        RoleResponse data = service.createRole(resquest);
        return ApiResponseUtil.success(data, SuccessCode.ROLE_CREATED);
    }

    @DeleteMapping("{role}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable String role) {
        service.deleteRole(role);
        return ApiResponseUtil.success(SuccessCode.ROLE_DELETED);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRole() {
        List<RoleResponse> data = service.getAllRole();
        return ApiResponseUtil.success(data, SuccessCode.ROLE_LISTED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllRole(){
        service.deleteAllRole();
        return ApiResponseUtil.success(SuccessCode.ROLE_DELETED_ALL);
    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<CategoryReponse> getCategoryById(@PathVariable String id) {
//        CategoryReponse data = categoryService.getCategory(id);
//        return  ApiResponseUtil.success(data, SuccessCode.GET_CATEGORY_BY_ID);
//    }
}
