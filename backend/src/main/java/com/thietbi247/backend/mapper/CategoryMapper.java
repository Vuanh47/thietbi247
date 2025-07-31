package com.thietbi247.backend.mapper;

import com.thietbi247.backend.dto.request.CategoryCreatResquest;
import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.responsitory.CategoryReponse;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.entity.Category;
import com.thietbi247.backend.entity.Device;

public interface CategoryMapper {
    Category toCategory(CategoryCreatResquest request);

//    void updateDevice(Category employee, DeviceCreatRequest request);
    CategoryReponse toCategoryReponse(Category category);
}
