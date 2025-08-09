package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.dto.request.CategoryCreatResquest;
import com.thietbi247.backend.dto.responsitory.CategoryReponse;
import com.thietbi247.backend.entity.Category;
import com.thietbi247.backend.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryCreatResquest request) {
        if (request == null) {
            return null;
        }
        return Category.builder()
                .category_name(request.getCategory_name())
                .updated_at(request.getUpdated_at())
                .status(request.getStatus())
                .build();
    }

    @Override
    public CategoryReponse toCategoryReponse(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryReponse.builder()
                .id(category.getId())
                .category_name(category.getCategory_name())
                .updated_at(category.getUpdated_at())
                .status(category.getStatus())
                .build();
    }
}
