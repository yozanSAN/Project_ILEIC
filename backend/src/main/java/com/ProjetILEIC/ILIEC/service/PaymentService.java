package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.Payment;
import com.ProjetILEIC.ILIEC.entity.Stagiaire;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.PaymentRepository;
import com.ProjetILEIC.ILIEC.repository.StagiaireRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StagiaireRepository stagiaireRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          StagiaireRepository stagiaireRepository,
                          UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.stagiaireRepository = stagiaireRepository;
        this.userRepository = userRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByStagiaire(Long stagiaireId) {
        return paymentRepository.findByStagiaire_Id(stagiaireId);
    }

    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByYear(Long stagiaireId, Integer year) {
        return paymentRepository.findByStagiaire_IdAndYear(stagiaireId, year);
    }

    @Transactional(readOnly = true)
    public boolean isPaidForMonth(Long stagiaireId, Integer month, Integer year) {
        return paymentRepository.existsByStagiaire_IdAndMonthAndYear(stagiaireId, month, year);
    }

    // --- CREATE ---

    public Payment recordPayment(Payment payment, Long stagiaireId, Long recordedById) {

        // Step 1 — business rule: can't record the same month twice
        if (paymentRepository.existsByStagiaire_IdAndMonthAndYear(
                stagiaireId, payment.getMonth(), payment.getYear())) {
            throw new DuplicateResourceException(
                    "Payment already recorded for month " + payment.getMonth()
                    + "/" + payment.getYear() + " for stagiaire " + stagiaireId);
        }

        // Step 2 — fetch dependencies
        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire not found: " + stagiaireId));

        User recordedBy = userRepository.findById(recordedById)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + recordedById));

        // Step 3 — attach and save
        payment.setStagiaire(stagiaire);
        payment.setRecordedBy(recordedBy);

        return paymentRepository.save(payment);
    }

    // --- UPDATE (mark as paid / change status) ---

    public Payment updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}
