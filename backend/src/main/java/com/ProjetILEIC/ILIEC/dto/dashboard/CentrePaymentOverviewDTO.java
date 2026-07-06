package com.ProjetILEIC.ILIEC.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentrePaymentOverviewDTO {
    private Long centreId;
    private String centreName;
    private long totalActiveStagiaires;
    private long paidStagiaires;
    private Double collectionRatePercent;
}
