package com.ProjetILEIC.ILIEC.dto;

import java.math.BigDecimal;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDTO {
    private Long id;
    private String name;
    private Integer durationYears;
    private BigDecimal monthlyFee;
}