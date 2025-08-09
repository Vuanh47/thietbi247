package com.thietbi247.backend.mapper.impl;

import com.thietbi247.backend.constant.ErrorCode;
import com.thietbi247.backend.dto.request.ErrorReportRequest;
import com.thietbi247.backend.dto.responsitory.DeviceResponse;
import com.thietbi247.backend.dto.responsitory.ErrorReportResponse;
import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.ErrorReport;
import com.thietbi247.backend.entity.User;
import com.thietbi247.backend.exception.AppException;
import com.thietbi247.backend.mapper.DeviceMapper;
import com.thietbi247.backend.mapper.ErrorReportMapper;
import com.thietbi247.backend.mapper.UserMapper;
import com.thietbi247.backend.repository.DeviceRepository;
import com.thietbi247.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ErrorReportMapperImpl implements ErrorReportMapper {

    private final DeviceMapper deviceMapper;
    private final UserMapper userMapper;

    @Override
    public ErrorReport toErrorReport(ErrorReportRequest request) {

        return ErrorReport.builder()
                .description(request.getDescription())
                .errorDate(LocalDateTime.now())
                .build();
    }

    @Override
    public ErrorReportResponse toErrorReportResponse(ErrorReport errorReport) {
        if (errorReport == null) {
            return null;
        }


        return ErrorReportResponse.builder()
                .id(errorReport.getId())
                .description(errorReport.getDescription())
                .errorDate(errorReport.getErrorDate())
                .devices(
                        errorReport.getDevices() != null
                                ? errorReport.getDevices().stream()
                                .map(deviceMapper::toDeviceResponse)
                                .collect(Collectors.toSet())
                                : Collections.emptySet()
                )
                .user(userMapper.toUserSimpleResponse(errorReport.getUser()))
                .build();
    }


}
