package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {
    private Long id;
    private Long stagiaireId;
    private String stagiaireFullName;
    private Long coursId;
    private String coursTitle;
    private Long recordedById;
    private String recordedByFullName;
    private LocalDate date;
    private String status;
    private String remarks;
}
