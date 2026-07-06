package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByfullName(String fullName);
    Optional<User> findByEmail(String email);
    long countByIsActiveTrue();
}
