package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.PaymentDTO;
import com.ProjetILEIC.ILIEC.dto.PaymentRequestDTO;
import com.ProjetILEIC.ILIEC.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments" )
@RequiredArgsConstructor

public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/stagiaire/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'STAGIAIRE')")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStagiaire(@PathVariable Long id) {
        List<PaymentDTO> payments = paymentService.getPaymentsByStagiaire(id);
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<PaymentDTO> recordPayment(
            @RequestBody PaymentRequestDTO requestDTO) {
        return new ResponseEntity<>(paymentService.recordPayment(requestDTO),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<PaymentDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(paymentService.updatePaymentStatus(id, status));
    }
}
