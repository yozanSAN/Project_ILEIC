package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {

    List<Filiere> findByCentre_Id(Long centreId);

    List<Filiere> findByProgram_Id(Long programId);

    List<Filiere> findByCentre_IdAndProgram_Id(Long centreId, Long programId);
}
