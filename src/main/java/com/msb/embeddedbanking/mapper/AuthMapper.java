package com.msb.embeddedbanking.mapper;
import static com.msb.embeddedbanking.util.AppConstant.BEARER_PREFIX;
import com.msb.embeddedbanking.controller.response.auth.LoginResponse;
import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import com.msb.embeddedbanking.repository.entity.User;
import com.msb.embeddedbanking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthMapper {

    private final JwtUtil jwtUtil;

    public LoginResponse toLoginResponse(String accessToken, String refreshTokenStr, User user, List<MenuResponse> menuResponse) {
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenStr)
                .tokenType(BEARER_PREFIX)
                .accessTokenExpiresIn(jwtUtil.getAccessTokenExpirationInSeconds())
                .refreshTokenExpiresIn(jwtUtil.getRefreshTokenExpirationInSeconds())
                .user(UserMapper.toResponse(user,menuResponse))
                .build();
    }
}
