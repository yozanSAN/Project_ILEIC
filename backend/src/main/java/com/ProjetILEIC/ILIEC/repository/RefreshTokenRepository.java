package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken ,Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser_Id(Long userId);

    @Modifying
    void deleteByUser_Id(Long userId);
    @Modifying
    void deleteByToken(String token);
}
