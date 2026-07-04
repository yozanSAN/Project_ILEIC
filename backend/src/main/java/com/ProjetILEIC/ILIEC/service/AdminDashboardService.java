package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.AdminDashboardDTO;
import com.ProjetILEIC.ILIEC.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminDashboardService {

    private final CentreRepository centreRepository;
    private final StagiaireRepository stagiaireRepository;
    private final FormateurRepository formateurRepository;
    private final SecretaryRepository secretaryRepository;
    private final AbsenceRepository absenceRepository;
    private final PaymentRepository paymentRepository;


     //Fetches and aggregates general system statistics for the admin dashboard.
    public AdminDashboardDTO getDashboardSummary() {
        return AdminDashboardDTO.builder()
                .totalCentres(centreRepository.count())
                .totalStagiaires(stagiaireRepository.count())
                .totalFormateurs(formateurRepository.count())
                .totalSecretaries(secretaryRepository.count())
                .totalAbsences(absenceRepository.count())
                .totalPayments(paymentRepository.count())
                .build();
    }
}
