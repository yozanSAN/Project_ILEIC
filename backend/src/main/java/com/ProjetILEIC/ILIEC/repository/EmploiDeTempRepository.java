package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.EmploiDeTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploiDeTempRepository extends JpaRepository<EmploiDeTemp, Long> {

    List<EmploiDeTemp> findByFiliere_Id(Long filiereId);

    List<EmploiDeTemp> findByCentre_Id(Long centreId);

    List<EmploiDeTemp> findByFormateur_Id(Long formateurId);

    List<EmploiDeTemp> findByFiliere_IdAndDayOfWeek(Long filiereId, String dayOfWeek);

    List<EmploiDeTemp> findByCentre_IdAndDayOfWeek(Long centreId, String dayOfWeek);
}
