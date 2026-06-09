package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretaryRepository extends JpaRepository<secretary, Long> {

    Optional<secretary> findByUser_Id(Long userId);

    List<secretary> findByCentre_Id(Long centreId);
}
