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

    // Update list methods and add toDTO
    public List<PaymentDTO> getPaymentsByStagiaire(Long stagiaireId) {
        return paymentRepository.findByStagiaire_Id(stagiaireId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

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

    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByYear(Long stagiaireId, Integer year) {
        return paymentRepository.findByStagiaire_IdAndYear(stagiaireId, year)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean isPaidForMonth(Long stagiaireId, Integer month, Integer year) {
        return paymentRepository.existsByStagiaire_IdAndMonthAndYear(stagiaireId, month, year);
    }

    // --- CREATE ---
    public PaymentDTO recordPayment(PaymentRequestDTO requestDTO, Long stagiaireId, Long recordedById) {

        // 1. Business rule check (read directly from RequestDTO)
        if (paymentRepository.existsByStagiaire_IdAndMonthAndYear(
                stagiaireId, requestDTO.getMonth(), requestDTO.getYear())) {
            throw new DuplicateResourceException("Payment already recorded...");
        }

        // 2. Convert incoming RequestDTO to database Entity
        Payment payment = new Payment();
        payment.setAmount(requestDTO.getAmount());
        payment.setMonth(requestDTO.getMonth());
        payment.setYear(requestDTO.getYear());
        payment.setPaymentMethod(requestDTO.getPaymentMethod());
        payment.setReference(requestDTO.getReference());

        // 3. Fetch relationships
        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId).orElseThrow(
                () -> new ResourceNotFoundException("Stagiaire not found"));
        User recordedBy = userRepository.findById(recordedById).orElseThrow(
                () -> new ResourceNotFoundException("user not found"));

        payment.setStagiaire(stagiaire);
        payment.setRecordedBy(recordedBy);

        // 4. Save to database
        Payment savedPayment = paymentRepository.save(payment);

        // 5. Convert saved Entity to the final outgoing PaymentDTO
        return toDTO(savedPayment);
    }

    // --- UPDATE (mark as paid / change status) ---
    public PaymentDTO updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        payment.setStatus(status);
        Payment updatedPayment = paymentRepository.save(payment);
        return toDTO(updatedPayment); // 💡 Map to DTO before returning
    }
    private Payment toEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setAmount(dto.getAmount());
        payment.setMonth(dto.getMonth());
        payment.setYear(dto.getYear());
        payment.setPaymentDate(dto.getPaymentDate());
        return payment;
    }
}
