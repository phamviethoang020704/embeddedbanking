package com.msb.embeddedbanking.mapper;

import com.msb.embeddedbanking.repository.entity.RefreshToken;
import com.msb.embeddedbanking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class RefreshTokenMapper {

    private final JwtUtil jwtUtil;

    public RefreshToken toEntity(Long userId, String token) {
        return RefreshToken.builder()
                .userId(userId)
                .token(token)
                .expiresAt(OffsetDateTime.now().plusSeconds(jwtUtil.getRefreshTokenExpirationInSeconds()))
                .build();
    }
}