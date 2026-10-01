package com.msb.embeddedbanking.controller.response;

import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import com.msb.embeddedbanking.controller.response.role.RoleForUserResponse;
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
@Schema(description = "Response chứa thông tin người dùng")
public class UserResponse {

    @Schema(description = "ID người dùng", example = "1")
    private Long id;

    @Schema(description = "Tên đăng nhập", example = "nguyen.van.an")
    private String username;

    @Schema(description = "Email người dùng", example = "nguyen.van.an@example.com")
    private String email;

    @Schema(description = "Tên đầy đủ của người dùng", example = "Nguyễn Văn An")
    private String fullName;

    @Schema(description = "Thông tin vai trò")
    private RoleForUserResponse role;

    @Schema(description = "thông tin menu")
    private List<MenuResponse> menus;
}
