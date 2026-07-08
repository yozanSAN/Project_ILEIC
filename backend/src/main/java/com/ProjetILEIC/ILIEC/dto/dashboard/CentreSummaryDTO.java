package com.ProjetILEIC.ILIEC.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentreSummaryDTO {
    private Long centreId;
    private String centreName;
    private Boolean isActive;
    private String secretaryName;
    private long stagiaireCount;
    private long formateurCount;
    private Double collectionRatePercent;
}
