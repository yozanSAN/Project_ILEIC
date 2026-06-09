package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStagiaire_Id(Long stagiaireId);

    Optional<Payment> findByStagiaire_IdAndMonthAndYear(Long stagiaireId, Integer month, Integer year);

    List<Payment> findByStagiaire_IdAndYear(Long stagiaireId, Integer year);

    List<Payment> findByStatus(String status);

    boolean existsByStagiaire_IdAndMonthAndYear(Long stagiaireId, Integer month, Integer year);
}
