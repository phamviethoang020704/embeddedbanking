package com.msb.embeddedbanking.controller.response.role;

import com.msb.embeddedbanking.controller.response.permission.PermissionForUserResponse;
import com.msb.embeddedbanking.enums.role.RoleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response vai trò")
public class RoleForUserResponse {

    @Schema(description = "ID vai trò", example = "1")
    private Long id;

    @Schema(description = "Tên vai trò", example = "Quản lý kho")
    private String name;

    @Schema(description = "Mô tả vai trò", example = "Quản lý nhập xuất kho và kiểm kê")
    private String description;

    @Schema(
            description = "Trạng thái vai trò",
            example = "ACTIVE",
            allowableValues = {"ACTIVE", "INACTIVE"})
    private RoleStatus status;

    @Schema(description = "Danh sách quyền của vai trò")
    private List<PermissionForUserResponse> permissions;
}

