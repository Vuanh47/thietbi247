package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.CategoryCreatResquest;
import com.thietbi247.backend.dto.responsitory.CategoryReponse;
import com.thietbi247.backend.entity.Category;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.CategoryMapper;
import com.thietbi247.backend.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository repository;
    CategoryMapper mapper;
    public CategoryReponse createCategory(CategoryCreatResquest resquest) {
        Category category = mapper.toCategory(resquest);
        category.setUpdated_at(LocalDateTime.now());
        repository.save(category);
        return mapper.toCategoryReponse(category);
    }

    public void deleteCategory(String id) {
        Category category = repository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.CATEGORY_NOT_EXISTS));
        repository.delete(category);
    }

    public List<CategoryReponse> getAllCategories() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(mapper::toCategoryReponse)
                .toList();
    }


    public void deleteAllCategory(){
        repository.deleteAll();
    }

    public CategoryReponse getCategory(String id) {
        Category category = repository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTS));
        return mapper.toCategoryReponse(category);
    }
}
