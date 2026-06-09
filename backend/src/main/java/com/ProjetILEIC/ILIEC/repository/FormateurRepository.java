package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {

    Optional<Formateur> findByUser_Id(Long userId);

    List<Formateur> findByCentres_Id(Long centreId);

    List<Formateur> findBySpecialityContainingIgnoreCase(String speciality);
}
