package com.ProjetILEIC.ILIEC.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtRiskStagiaireDTO {
    private Long stagiaireId;
    private String stagiaireName;
    private String centreName;
    private String filiereName;
    private long absenceCount;
}