package com.msb.embeddedbanking.service;

import com.msb.embeddedbanking.repository.entity.RefreshToken;

public interface RefreshTokenService {
    void create(RefreshToken refreshToken);
}
