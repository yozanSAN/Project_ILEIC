package com.ProjetILEIC.ILIEC.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiliereAbsenceOverviewDTO {
    private Long filiereId;
    private String filiereName;
    private long totalStagiaires;
    private long flaggedStagiairesCount;
    private Double absenceRatePercent;
}