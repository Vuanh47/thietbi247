package com.thietbi247.backend.controller;

import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.request.CategoryCreatResquest;
import com.thietbi247.backend.dto.request.PermissionRequest;
import com.thietbi247.backend.dto.responsitory.ApiResponse;
import com.thietbi247.backend.dto.responsitory.CategoryReponse;
import com.thietbi247.backend.dto.responsitory.PermissionResponse;
import com.thietbi247.backend.service.CategoryService;
import com.thietbi247.backend.service.PermissionService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService service;

    @PostMapping
    public ResponseEntity<List<PermissionResponse>> addPermissions(@RequestBody List<PermissionRequest> requests) {
        List<PermissionResponse> data = requests.stream()
                .map(service::createPermission)
                .toList();

        return ApiResponseUtil.success(data, SuccessCode.PERMISSION_CREATED);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable String id) {
        service.deletePermission(id);
        return ApiResponseUtil.success(SuccessCode.PERMISSION_DELETED);
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getAllPermission() {
        List<PermissionResponse> data = service.getAllPermission();
        return ApiResponseUtil.success(data, SuccessCode.PERMISSION_LISTED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllCategory(){
        service.deleteAllPermission();
        return ApiResponseUtil.success(SuccessCode.PERMISSION_DELETED_All);
    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<CategoryReponse> getCategoryById(@PathVariable String id) {
//        CategoryReponse data = categoryService.getCategory(id);
//        return  ApiResponseUtil.success(data, SuccessCode.GET_CATEGORY_BY_ID);
//    }
}
