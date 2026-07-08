package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.dashboard.*;
import com.ProjetILEIC.ILIEC.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    @GetMapping("/stats")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<DashboardStatsDTO> getStats() {
        return ResponseEntity.ok(dashboardService.getSystemStats());
    }

    @GetMapping("/centres")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<CentreSummaryDTO>> getCentreSummaries() {
        return ResponseEntity.ok(dashboardService.getCentreSummaries());
    }

    @GetMapping("/at-risk")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<AtRiskStagiaireDTO>> getAtRiskStagiaires() {
        return ResponseEntity.ok(dashboardService.getAtRiskStagiaires());
    }

    @GetMapping("/payments/overview")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<CentrePaymentOverviewDTO>> getPaymentOverview(
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {
        int targetMonth = (month != null) ? month : LocalDate.now().getMonthValue();
        int targetYear = (year != null) ? year : LocalDate.now().getYear();
        return ResponseEntity.ok(dashboardService.getPaymentOverview(targetMonth, targetYear));
    }

    @GetMapping("/absences/overview")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<FiliereAbsenceOverviewDTO>> getAbsenceOverview(
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {
        int targetMonth = (month != null) ? month : LocalDate.now().getMonthValue();
        int targetYear = (year != null) ? year : LocalDate.now().getYear();
        return ResponseEntity.ok(dashboardService.getAbsenceOverview(targetMonth, targetYear));
    }
}