package com.msb.embeddedbanking.service.impl;

import com.msb.embeddedbanking.repository.RefreshTokenRepository;
import com.msb.embeddedbanking.repository.entity.RefreshToken;
import com.msb.embeddedbanking.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void create(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }
}
