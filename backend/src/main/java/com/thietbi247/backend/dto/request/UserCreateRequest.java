package com.thietbi247.backend.dto.request;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String userName;
    @Email
    String email;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    String phone;
    String address;
    Integer room;
    List<String> roles;
}