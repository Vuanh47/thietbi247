package com.thietbi247.backend.dto.responsitory;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.Permissions;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;

}
