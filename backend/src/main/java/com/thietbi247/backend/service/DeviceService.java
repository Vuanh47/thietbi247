package com.thietbi247.backend.service;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.DeviceMapper;
import com.thietbi247.backend.repository.DeviceRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceService {
    DeviceMapper mapper;
    DeviceRepository repository;
    public DeviceResponse  createDevice(DeviceCreatRequest request) {
        Device device = mapper.toDevice(request);
        device.setDatePurchase(LocalDateTime.now());
        repository.save(device);
        return mapper.toDeviceResponse(device);
    }

    public List<DeviceResponse> getAllDevices() {
        List<Device> devices = repository.findAll();
        return devices.stream()
                .map(mapper::toDeviceResponse)
                .collect(Collectors.toList());
    }

    public void deleteDevice(String id){
        if (!repository.existsById(id)){
            throw new AppException(ErrorCode.DEVICE_NOT_EXISTS);
        }
        repository.deleteById(id);
    }
}
