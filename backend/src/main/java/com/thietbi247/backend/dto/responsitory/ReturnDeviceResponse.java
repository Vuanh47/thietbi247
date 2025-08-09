package com.thietbi247.backend.dto.responsitory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thietbi247.backend.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReturnDeviceResponse {
    String id;
    LocalDateTime returnDate;
    UserResponse user;
    List<DeviceResponse> devices;
}
