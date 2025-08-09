package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.request.DeviceUpdateRequest;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.entity.Category;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.mapper.DeviceMapper;
import com.thietbi247.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceMapperImpl implements DeviceMapper {

    private final CategoryRepository categoryRepository;

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
                .productName(request.getProductName())
                .category(category)
                .quantity(request.getQuantity())
                .status(request.getStatus())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

    @Override
    public void updateDevice(Device device, DeviceUpdateRequest request) {
        device.setProductName(request.getProductName());
        device.setQuantity(request.getQuantity());
        device.setStatus(request.getStatus());
        device.setImage(request.getImage());
        device.setDescription(request.getDescription());
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
                .build();
    }
}
