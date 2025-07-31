package com.thietbi247.backend.dto.request;
import jakarta.persistence.ElementCollection;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String userName;
    String email;
    String password;
    String phone;
    String address;
    Integer room;
    @ElementCollection
    Set<String> roles;
}