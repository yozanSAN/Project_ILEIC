package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.ModulesFormateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModulesFormateurRepository extends JpaRepository<ModulesFormateur, Long> {

    List<ModulesFormateur> findByFormateur_Id(Long formateurId);

    List<ModulesFormateur> findByCours_Id(Long coursId);

    List<ModulesFormateur> findByFormateur_IdAndAcademicYear(Long formateurId, Integer academicYear);

    boolean existsByFormateur_IdAndCours_Id(Long formateurId, Long coursId);
}
