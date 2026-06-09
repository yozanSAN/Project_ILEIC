package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {

    Optional<Cours> findByCode(String code);

    List<Cours> findByFiliere_Id(Long filiereId);

    List<Cours> findByFiliere_IdAndYearLevel(Long filiereId, Integer yearLevel);

    List<Cours> findByFiliere_IdAndSemester(Long filiereId, Integer semester);

    boolean existsByCode(String code);
}
