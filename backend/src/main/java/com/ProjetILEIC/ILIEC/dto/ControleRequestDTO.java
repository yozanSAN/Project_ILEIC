package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleRequestDTO {
    private Long coursId;
    private Long filiereId;
    private String title;
    private String type;
    private LocalDate examDate;
    private BigDecimal coefficient;
    private String status;
}