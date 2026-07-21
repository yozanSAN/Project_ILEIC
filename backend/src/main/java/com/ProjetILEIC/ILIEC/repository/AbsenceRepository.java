package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findByStagiaire_Id(Long stagiaireId);

    List<Absence> findByCours_Id(Long coursId);

    List<Absence> findByStagiaire_IdAndCours_Id(Long stagiaireId, Long coursId);

    List<Absence> findByStagiaire_IdAndDate(Long stagiaireId, LocalDate date);

    List<Absence> findByDateBetween(LocalDate from, LocalDate to);

    long countByStagiaire_IdAndStatus(Long stagiaireId, String status);

    @Query("SELECT a.stagiaire.id, COUNT(a) FROM Absence a " +
            "WHERE a.status = 'UNJUSTIFIED' " +
            "GROUP BY a.stagiaire.id " +
            "HAVING COUNT(a) >= :limit")
    List<Object[]> findStagiaireAbsenceCountsAtRisk(@Param("limit") long limit);
}
