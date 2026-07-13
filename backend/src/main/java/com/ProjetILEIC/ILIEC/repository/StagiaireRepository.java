package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {

    Optional<Stagiaire> findByRegistrationNumber(String registrationNumber);

    List<Stagiaire> findByFiliere_Id(Long filiereId);

    List<Stagiaire> findByCentre_Id(Long centreId);

    List<Stagiaire> findByFiliere_IdAndCentre_Id(Long filiereId, Long centreId);

    List<Stagiaire> findByStatus(String status);

    boolean existsByRegistrationNumber(String registrationNumber);

    Optional<Stagiaire> findByUser_Id(Long userId);

    boolean existsByCentre_IdAndUser_IsActiveTrue(Long centreId);
}
