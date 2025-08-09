package com.thietbi247.backend.mapper;


import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.request.ReturnDeviceRequest;
import com.thietbi247.backend.dto.responsitory.ReturnDeviceResponse;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.ReturnDevice;

public interface ReturnDeviceMapper {
    ReturnDevice toReturnDevice(ReturnDeviceRequest request);

    void updateDevice(Device employee, DeviceCreatRequest request);
    ReturnDeviceResponse toReturnDeviceResponse(ReturnDevice returnDevice);
}
