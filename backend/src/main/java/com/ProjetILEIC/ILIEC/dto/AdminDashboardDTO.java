package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}