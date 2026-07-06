package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {

    Optional<Centre> findByName(String name);

    Optional<Centre> findByEmail(String email);

    long countByIsActiveTrue();
}
