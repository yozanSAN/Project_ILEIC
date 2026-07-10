package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.RefreshToken;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.repository.RefreshTokenRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    // Resolves property from application.properties; defaults to 7 days (in ms)
    @Value("${app.jwt.refresh-expiration-ms:604800000}")
    private Long refreshExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

     //Creates a unique refresh token for an active user and clears their stale records.
    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Security requirement: Instantly reject deactivated users attempting a login/refresh session
        if (!user.getIsActive()) {
            throw new DisabledException("This account has been deactivated.");
        }

        // Clean slate: Evict prior tokens belonging to this account ID
        refreshTokenRepository.deleteByUser_Id(userId);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiresAt(Instant.now().plusMillis(refreshExpirationMs))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Verifies token lifecycle validity and tests real-time active user status.
     */
    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
        // Real-time ban enforcement: Kick out users whose isActive flag was turned off mid-session
        if (!token.getUser().getIsActive()) {
            refreshTokenRepository.delete(token);
            throw new DisabledException("User account is deactivated. Session revoked.");
        }

        // Standard timing expiration validation
        if (token.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token has expired. Please sign in again.");
        }

        return token;
    }

    /**
     * Evicts a token from the persistent store to force an instant user logout.
     */
    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}