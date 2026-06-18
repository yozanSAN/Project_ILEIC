package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long stagiaireId;
    private String stagiaireName;
    private Integer month;
    private Integer year;
    private BigDecimal amount;
    private String status;
    private String paymentMethod;
    private String recordedByEmail;
}
