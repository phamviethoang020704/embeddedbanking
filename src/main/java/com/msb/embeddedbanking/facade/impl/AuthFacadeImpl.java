package com.msb.embeddedbanking.facade.impl;

import com.msb.embeddedbanking.controller.request.auth.LoginRequest;
import com.msb.embeddedbanking.controller.response.auth.LoginResponse;
import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import com.msb.embeddedbanking.exception.InvalidCredentialException;
import com.msb.embeddedbanking.facade.AuthFacade;
import com.msb.embeddedbanking.mapper.AuthMapper;
import com.msb.embeddedbanking.mapper.RefreshTokenMapper;
import com.msb.embeddedbanking.repository.entity.Menu;
import com.msb.embeddedbanking.repository.entity.RefreshToken;
import com.msb.embeddedbanking.repository.entity.User;
import com.msb.embeddedbanking.service.MenuService;
import com.msb.embeddedbanking.service.RefreshTokenService;
import com.msb.embeddedbanking.service.UserService;
import com.msb.embeddedbanking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenMapper refreshTokenMapper;
    private final RefreshTokenService refreshTokenService;
    private final AuthMapper authMapper;
    private final MenuService menuService;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<User> user = userService.getUserByUsername(username);
        if (user.isEmpty()){
            throw new InvalidCredentialException();
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new InvalidCredentialException();
        }

        return generateLoginResponse(user.get());
    }

//    @Override
//    @Transactional
//    public LoginResponse refreshToken(String refreshTokenStr) {
//        if (!jwtUtil.isValidToken(refreshTokenStr)) {
//            throw new BusinessFlowException("Refresh token không hợp lệ");
//        }
//
//        if (!jwtUtil.isRefreshToken(refreshTokenStr)) {
//            throw new BusinessFlowException("Token không phải là refresh token");
//        }
//
//        RefreshToken existRefreshToken = refreshTokenService.getByToken(refreshTokenStr);
//
//        log.info("Refreshing token for user ID {}", existRefreshToken.getUserId());
//
//        if (existRefreshToken.isRevoked()) {
//            throw new BusinessFlowException("Refresh token đã bị thu hồi");
//        }
//
//        if (existRefreshToken.isExpired()) {
//            throw new BusinessFlowException("Refresh token đã hết hạn");
//        }
//
//        User user = userService.getById(existRefreshToken.getUserId());
//
//        refreshTokenService.revoke(existRefreshToken);
//
//        return generateLoginResponse(user);
//    }
    private LoginResponse generateLoginResponse(User user) {
        log.info("Generating login response for user: {}", user.getUsername());

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshTokenStr = jwtUtil.generateRefreshToken(user);

        RefreshToken refreshToken = refreshTokenMapper.toEntity(user.getId(), refreshTokenStr);
        refreshTokenService.create(refreshToken);
        List<MenuResponse> menuResponse = menuService.toMenuResponse(user);
        return authMapper.toLoginResponse(accessToken, refreshTokenStr, user,menuResponse);
    }
    private MenuResponse buildMenuResponse(
            Menu menu,
            Map<Long, Menu> menuMap
    ) {

        if (menu == null) {
            return null;
        }

        MenuResponse parentResponse = null;

        if (menu.getParentId() != null) {
            Menu parent = menuMap.get(menu.getParentId());

            parentResponse = buildMenuResponse(parent, menuMap);
        }

        return MenuResponse.builder()
                .id(menu.getId())
                .code(menu.getCode())
                .parent(parentResponse)
                .build();
    }
}
