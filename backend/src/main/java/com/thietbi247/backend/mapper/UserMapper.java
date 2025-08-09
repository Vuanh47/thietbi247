package com.thietbi247.backend.mapper;

import com.thietbi247.backend.dto.request.UserCreateRequest;
import com.thietbi247.backend.dto.responsitory.UserResponse;
import com.thietbi247.backend.entity.User;



public interface UserMapper {
    User toUser(UserCreateRequest request);

    void updateUser(User user, UserCreateRequest request);
    UserResponse toUserResponse(User user);

    UserResponse toUserSimpleResponse(User user);
}
