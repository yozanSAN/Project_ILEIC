package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.*;
import com.ProjetILEIC.ILIEC.dto.dashboard.AtRiskStagiaireDTO;
import com.ProjetILEIC.ILIEC.dto.dashboard.CentrePaymentOverviewDTO;
import com.ProjetILEIC.ILIEC.dto.dashboard.CentreSummaryDTO;
import com.ProjetILEIC.ILIEC.dto.dashboard.DashboardStatsDTO;
import com.ProjetILEIC.ILIEC.dto.dashboard.FiliereAbsenceOverviewDTO;
import com.ProjetILEIC.ILIEC.entity.*;
import com.ProjetILEIC.ILIEC.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminDashboardService {

    private static final long ABSENCE_LIMIT = 10;

    private final CentreRepository centreRepository;
    private final UserRepository userRepository;
    private final StagiaireRepository stagiaireRepository;
    private final FormateurRepository formateurRepository;
    private final SecretaryRepository secretaryRepository;
    private final AbsenceRepository absenceRepository;
    private final PaymentRepository paymentRepository;
    private final DocumentRepository documentRepository;
    private final FiliereRepository filiereRepository;

    // ================= 1. SYSTEM-WIDE STATS =================
    public DashboardStatsDTO getSystemStats() {
        long totalCentres = centreRepository.count();
        long activeCentres = centreRepository.countByIsActiveTrue();
        long inactiveCentres = totalCentres - activeCentres;

        long totalUsers = userRepository.count();
        long activeUsers = userRepository.countByIsActiveTrue();
        long inactiveUsers = totalUsers - activeUsers;

        long totalStagiaires = stagiaireRepository.count();
        long totalFormateurs = formateurRepository.count();
        long totalSecretaries = secretaryRepository.count();
        long pendingDocuments = documentRepository.countByStatus("PENDING");

        return new DashboardStatsDTO(
                totalCentres, activeCentres, inactiveCentres,
                totalUsers, activeUsers, inactiveUsers,
                totalStagiaires, totalFormateurs, totalSecretaries,
                pendingDocuments
        );
    }

    // ================= 2. PER-CENTRE SUMMARY =================
    public List<CentreSummaryDTO> getCentreSummaries() {
        LocalDate now = LocalDate.now();
        List<CentrePaymentOverviewDTO> currentMonthPayments = getPaymentOverview(now.getMonthValue(), now.getYear());

        Map<Long, Double> rateByCentre = currentMonthPayments.stream()
                .collect(Collectors.toMap(
                        CentrePaymentOverviewDTO::getCentreId,
                        CentrePaymentOverviewDTO::getCollectionRatePercent,
                        (v1, v2) -> v1 // Merge function to handle duplicate keys safely
                ));

        List<CentreSummaryDTO> result = new ArrayList<>();

        for (Centre centre : centreRepository.findAll()) {
            long stagiaireCount = stagiaireRepository.findByCentre_Id(centre.getId()).size();
            long formateurCount = formateurRepository.findByCentres_Id(centre.getId()).size();

            List<Secretary> secretaries = secretaryRepository.findByCentre_Id(centre.getId());
            String secretaryName = secretaries.isEmpty() ? null : secretaries.get(0).getUser().getFullName();

            result.add(new CentreSummaryDTO(
                    centre.getId(),
                    centre.getName(),
                    centre.getIsActive(),
                    secretaryName,
                    stagiaireCount,
                    formateurCount,
                    rateByCentre.getOrDefault(centre.getId(), 0.0)
            ));
        }
        return result;
    }

    // ================= 3. AT-RISK STAGIAIRES =================
    public List<AtRiskStagiaireDTO> getAtRiskStagiaires() {
        List<Object[]> rows = absenceRepository.findStagiaireAbsenceCountsAtRisk(ABSENCE_LIMIT);
        if (rows.isEmpty()) {
            return List.of();
        }

        Map<Long, Long> countByStagiaireId = new HashMap<>();
        for (Object[] row : rows) {
            countByStagiaireId.put((Long) row[0], (Long) row[1]);
        }

        List<Stagiaire> stagiaires = stagiaireRepository.findAllById(countByStagiaireId.keySet());

        return stagiaires.stream()
                .map(s -> new AtRiskStagiaireDTO(
                        s.getId(),
                        s.getUser().getFullName(),
                        s.getCentre().getName(),
                        s.getFiliere().getName(),
                        countByStagiaireId.get(s.getId())
                ))
                .collect(Collectors.toList());
    }

    // ================= 4. PAYMENT BREAKDOWN =================
    public List<CentrePaymentOverviewDTO> getPaymentOverview(int month, int year) {
        List<Payment> paidThisPeriod = paymentRepository.findByMonthAndYear(month, year).stream()
                .filter(p -> "PAID".equalsIgnoreCase(p.getStatus()))
                .collect(Collectors.toList());

        Map<Long, Set<Long>> paidStagiaireIdsByCentre = new HashMap<>();
        for (Payment p : paidThisPeriod) {
            if (p.getStagiaire() != null && p.getStagiaire().getCentre() != null) {
                Long centreId = p.getStagiaire().getCentre().getId();
                paidStagiaireIdsByCentre
                        .computeIfAbsent(centreId, k -> new HashSet<>())
                        .add(p.getStagiaire().getId());
            }
        }

        List<CentrePaymentOverviewDTO> result = new ArrayList<>();

        for (Centre centre : centreRepository.findAll()) {
            List<Stagiaire> activeStagiaires = stagiaireRepository.findByCentre_Id(centre.getId()).stream()
                    .filter(s -> !"GRADUATED".equalsIgnoreCase(s.getStatus()))
                    .collect(Collectors.toList());

            long totalActive = activeStagiaires.size();
            Set<Long> paidIds = paidStagiaireIdsByCentre.getOrDefault(centre.getId(), Set.of());

            long paidCount = activeStagiaires.stream()
                    .filter(s -> paidIds.contains(s.getId()))
                    .count();

            Double rate = (totalActive == 0) ? 0.0 : (paidCount * 100.0) / totalActive;

            result.add(new CentrePaymentOverviewDTO(
                    centre.getId(), centre.getName(), totalActive, paidCount, rate
            ));
        }
        return result;
    }

    // ================= 5. ABSENCE BREAKDOWN =================
    public List<FiliereAbsenceOverviewDTO> getAbsenceOverview(int month, int year) {
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = from.withDayOfMonth(from.lengthOfMonth());

        List<Absence> unjustifiedThisPeriod = absenceRepository.findByDateBetween(from, to).stream()
                .filter(a -> "UNJUSTIFIED".equalsIgnoreCase(a.getStatus()))
                .collect(Collectors.toList());

        Map<Long, Set<Long>> flaggedStagiaireIdsByFiliere = new HashMap<>();
        for (Absence a : unjustifiedThisPeriod) {
            if (a.getStagiaire() != null && a.getStagiaire().getFiliere() != null) {
                Long filiereId = a.getStagiaire().getFiliere().getId();
                flaggedStagiaireIdsByFiliere
                        .computeIfAbsent(filiereId, k -> new HashSet<>())
                        .add(a.getStagiaire().getId());
            }
        }

        List<FiliereAbsenceOverviewDTO> result = new ArrayList<>();

        for (Filiere filiere : filiereRepository.findAll()) {
            long totalStagiaires = stagiaireRepository.findByFiliere_Id(filiere.getId()).size();
            Set<Long> flagged = flaggedStagiaireIdsByFiliere.getOrDefault(filiere.getId(), Set.of());
            long flaggedCount = flagged.size();

            Double rate = (totalStagiaires == 0) ? 0.0 : (flaggedCount * 100.0) / totalStagiaires;

            result.add(new FiliereAbsenceOverviewDTO(
                    filiere.getId(), filiere.getName(), totalStagiaires, flaggedCount, rate
            ));
        }
        return result;
    }
}