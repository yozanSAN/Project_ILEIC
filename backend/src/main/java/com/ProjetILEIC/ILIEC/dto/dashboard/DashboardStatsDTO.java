package com.ProjetILEIC.ILIEC.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private long totalCentres;
    private long activeCentres;
    private long inactiveCentres;
    private long totalUsers;
    private long activeUsers;
    private long inactiveUsers;
    private long totalStagiaires;
    private long totalFormateurs;
    private long totalSecretaries;
    private long pendingDocuments;
}