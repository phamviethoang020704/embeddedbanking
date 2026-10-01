package com.msb.embeddedbanking.controller.response.auth;


import com.msb.embeddedbanking.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response đăng nhập thành công")
public class LoginResponse {

    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzUxMiJ9...")
    private String accessToken;

    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzUxMiJ9...")
    private String refreshToken;

    @Schema(description = "Loại token", example = "Bearer")
    private String tokenType;

    @Schema(description = "Thời gian hết hạn của access token (giây)", example = "3600")
    private Long accessTokenExpiresIn;

    @Schema(description = "Thời gian hết hạn của refresh token (giây)", example = "604800")
    private Long refreshTokenExpiresIn;

    @Schema(description = "Thông tin người dùng")
    private UserResponse user;
}
