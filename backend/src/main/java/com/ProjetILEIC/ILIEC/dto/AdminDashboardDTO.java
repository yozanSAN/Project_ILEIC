package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardDTO {
    private long totalCentres;
    private long totalStagiaires;
    private long totalFormateurs;
    private long totalSecretaries;
    private long totalAbsences;
    private long totalPayments;
    // 2. Extra analytical data fields
    private List<String> atRiskStagiaires;                // e.g., ["John Doe (12 absences)", "Jane Smith (10 absences)"]
    private List<Map<String, Object>> centreSummaries;    // Dynamic key-value pairs for centre data
    private Map<String, Object> monthlyPaymentOverview;   // Dynamic payment data for a specific month
    private Map<String, Object> monthlyAbsenceOverview;   // Dynamic absence data per filière
}