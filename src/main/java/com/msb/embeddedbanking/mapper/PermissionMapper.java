package com.msb.embeddedbanking.mapper;

import com.msb.embeddedbanking.controller.response.permission.PermissionForUserResponse;
import com.msb.embeddedbanking.controller.response.role.RoleForUserResponse;
import com.msb.embeddedbanking.repository.entity.Permission;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@UtilityClass
public class PermissionMapper {
    public List<PermissionForUserResponse> toForUserResponses(List<Permission> permissions) {
        return permissions.stream()
                .map(PermissionMapper::toForUserResponse)
                .collect(Collectors.toList());
    }
    public PermissionForUserResponse toForUserResponse(Permission permission) {
        return PermissionForUserResponse.builder()
                .id(permission.getId())
                .code(permission.getCode())
                .name(permission.getName())
                .description(permission.getDescription())
                .status(permission.getStatus().getValue())
                .build();
    }
}
