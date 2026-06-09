package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Controle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControleRepository extends JpaRepository<Controle, Long> {

    List<Controle> findByCours_Id(Long coursId);

    List<Controle> findByFiliere_Id(Long filiereId);

    List<Controle> findByCours_IdAndFiliere_Id(Long coursId, Long filiereId);

    List<Controle> findByStatus(String status);

    List<Controle> findByType(String type);
}
