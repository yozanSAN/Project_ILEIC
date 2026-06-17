package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceRequestDTO {
    private Long stagiaireId;
    private Long coursId;
    private Long recordedById;
    private LocalDate date;
    private String status;
    private String remarks;
}