package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.entity.Category;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.mapper.DeviceMapper;
import com.thietbi247.backend.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapperImpl implements DeviceMapper {

    private final CategoryRepository categoryRepository;

    public DeviceMapperImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Device toDevice(DeviceCreatRequest request) {
        if (request == null) {
            return  null;
        }

        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với ID: " + request.getCategoryId()));
        }

        return Device.builder()
                .id(request.getId())
                .productName(request.getProductName())
                .category(category)
                .quantity(request.getQuantity())
                .status(request.getStatus())
                .image(request.getImage())
                .description(request.getDescription())
                .datePurchase(request.getDatePurchase())
                .build();
    }

    @Override
    public void updateDevice(Device employee, DeviceCreatRequest request) {

    }

    @Override
    public DeviceResponse toDeviceResponse(Device device) {
        if (device == null) {
            return null;
        }
        String categoryName = null;
        if (device.getCategory() != null) {
            categoryName = device.getCategory().getCategory_name();
        }

        return DeviceResponse.builder()
                .id(device.getId())
                .productName(device.getProductName())
                .quantity(device.getQuantity())
                .categoryId(categoryName)
                .status(device.getStatus())
                .image(device.getImage())
                .description(device.getDescription())
                .datePurchase(device.getDatePurchase())
                .build();
    }
}
