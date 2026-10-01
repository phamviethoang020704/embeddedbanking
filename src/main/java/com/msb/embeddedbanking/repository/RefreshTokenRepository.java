package com.msb.embeddedbanking.repository;

import com.msb.embeddedbanking.repository.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
