package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;
    private Long stagiaireId;
    private String stagiaireFullName;
    private Long controleId;
    private String controleTitle;
    private Long recordedById;
    private String recordedByFullName;
    private BigDecimal gradeValue;
    private String remarks;
}
