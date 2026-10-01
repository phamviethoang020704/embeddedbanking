package com.msb.embeddedbanking.mapper;

import com.msb.embeddedbanking.controller.response.role.RoleForUserResponse;
import com.msb.embeddedbanking.repository.entity.Permission;
import com.msb.embeddedbanking.repository.entity.Role;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RoleMapper {
    public RoleForUserResponse toResponseForUser(Role role) {
        List<Permission> permissions = new ArrayList<>(role.getPermissions());

        return RoleForUserResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .status(role.getStatus())
                .permissions(
                        permissions.isEmpty()
                                ? new ArrayList<>()
                                : PermissionMapper.toForUserResponses(permissions))
                .build();
    }
}
