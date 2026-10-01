package com.msb.embeddedbanking.mapper;

import com.msb.embeddedbanking.controller.response.UserResponse;
import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import com.msb.embeddedbanking.repository.entity.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
@UtilityClass
public class UserMapper {
    public UserResponse toResponse(User user, List<MenuResponse> menuResponse) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(!Objects.isNull(user.getRole()) ? RoleMapper.toResponseForUser(user.getRole()) : null)
                .menus(menuResponse)
                .build();
    }
}
