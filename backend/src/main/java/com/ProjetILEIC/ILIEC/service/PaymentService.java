package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.PaymentDTO;
import com.ProjetILEIC.ILIEC.dto.PaymentRequestDTO;
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
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)  // fix 1: was missing
    public List<PaymentDTO> getPaymentsByStagiaire(Long stagiaireId) {
        return paymentRepository.findByStagiaire_Id(stagiaireId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByYear(Long stagiaireId, Integer year) {
        return paymentRepository.findByStagiaire_IdAndYear(stagiaireId, year)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean isPaidForMonth(Long stagiaireId, Integer month, Integer year) {
        return paymentRepository.existsByStagiaire_IdAndMonthAndYear(
                stagiaireId, month, year);
    }

    // --- CREATE ---

    public PaymentDTO recordPayment(PaymentRequestDTO dto) {  // fix 2: single DTO param
        if (paymentRepository.existsByStagiaire_IdAndMonthAndYear(
                dto.getStagiaireId(), dto.getMonth(), dto.getYear())) {
            throw new DuplicateResourceException(
                    "Payment already recorded for month " + dto.getMonth()
                            + "/" + dto.getYear()
                            + " for stagiaire " + dto.getStagiaireId());
        }

        Stagiaire stagiaire = stagiaireRepository.findById(dto.getStagiaireId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Stagiaire not found: " + dto.getStagiaireId()));

        User recordedBy = userRepository.findById(dto.getRecordedById())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found: " + dto.getRecordedById()));

        Payment payment = new Payment();
        payment.setStagiaire(stagiaire);
        payment.setRecordedBy(recordedBy);
        payment.setMonth(dto.getMonth());
        payment.setYear(dto.getYear());
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());   // fix 2: was missing
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(dto.getStatus());              // fix 2: was missing
        payment.setReference(dto.getReference());

        return toDTO(paymentRepository.save(payment));
    }

    // --- UPDATE ---

    public PaymentDTO updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with id: " + id));

        payment.setStatus(status);
        return toDTO(paymentRepository.save(payment));
    }

    // --- DTO CONVERSION ---

    public PaymentDTO toDTO(Payment p) {
        return new PaymentDTO(
                p.getId(),
                p.getStagiaire().getId(),
                p.getStagiaire().getUser().getFullName(),
                p.getMonth(),
                p.getYear(),
                p.getAmount(),
                p.getPaymentDate(),
                p.getPaymentMethod(),
                p.getStatus(),
                p.getReference(),
                p.getRecordedBy().getId(),
                p.getRecordedBy().getFullName()
        );
    }
}