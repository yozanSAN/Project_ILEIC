package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleDTO {
    private Long id;
    private Long coursId;
    private String coursName;
    private Long filiereId;
    private String filiereName;
    private String title;
    private String type;
    private LocalDate examDate;
    private BigDecimal coefficient;
    private String status;
}