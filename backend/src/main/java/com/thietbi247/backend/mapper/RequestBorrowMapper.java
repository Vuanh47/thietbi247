package com.thietbi247.backend.mapper;


import com.thietbi247.backend.dto.request.DeviceCreatRequest;
import com.thietbi247.backend.dto.request.RequestBorowRequest;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.dto.responsitory.RequestBorrowResponse;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.RequestBorrow;

public interface RequestBorrowMapper {
    RequestBorrow toRequestBorrow(RequestBorowRequest request);

    void updateDevice(Device employee, DeviceCreatRequest request);
    RequestBorrowResponse toRequestBorrowResponse(RequestBorrow requestBorrow);
}
