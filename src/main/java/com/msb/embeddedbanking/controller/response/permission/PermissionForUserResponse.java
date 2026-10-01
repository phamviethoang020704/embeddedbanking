package com.msb.embeddedbanking.controller.response.permission;


import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response quyền")
public class PermissionForUserResponse {

    @Schema(description = "ID quyền", example = "1")
    private Long id;

    @Schema(description = "Mã quyền (code)", example = "VIEW_PRODUCTS")
    private String code;

    @Schema(description = "Tên quyền", example = "Xem sản phẩm")
    private String name;

    @Schema(description = "Mô tả quyền", example = "Quyền xem danh sách và chi tiết sản phẩm")
    private String description;

    @Schema(
            description = "Trạng thái quyền",
            example = "ACTIVE",
            allowableValues = {"ACTIVE", "INACTIVE"})
    private String status;
}
