package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequestDTO {
    private Long stagiaireId;
    private Long controleId;
    private Long recordedById;
    private BigDecimal gradeValue;
    private String remarks;
}