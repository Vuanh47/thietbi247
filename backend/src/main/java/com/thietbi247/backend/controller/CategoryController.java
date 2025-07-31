package com.thietbi247.backend.controller;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.constant.SuccessCode;
import com.thietbi247.backend.dto.request.CategoryCreatResquest;
import com.thietbi247.backend.dto.responsitory.ApiResponse;
import com.thietbi247.backend.dto.responsitory.CategoryReponse;
import com.thietbi247.backend.service.CategoryService;
import com.thietbi247.backend.util.ApiResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryReponse> addCategory(@RequestBody CategoryCreatResquest resquest) {
        CategoryReponse data = categoryService.createCategory(resquest);
        return ApiResponseUtil.success(data, SuccessCode.CATEGORY_CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ApiResponseUtil.success(SuccessCode.CATEGORY_DELETED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryReponse>> getAllCategories() {
        List<CategoryReponse> data = categoryService.getAllCategories();
        return ApiResponseUtil.success(data, SuccessCode.CATEGORY_LISTED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllCategory(){
        categoryService.deleteAllCategory();
        return ApiResponseUtil.success(SuccessCode.CATEGORY_DELETED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryReponse> getCategoryById(@PathVariable String id) {
        CategoryReponse data = categoryService.getCategory(id);
        return  ApiResponseUtil.success(data, SuccessCode.GET_CATEGORY_BY_ID);
    }
}
