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
    // Stagiaire Details
    private Long stagiaireId;
    private String stagiaireFullName;

    // Cours Details
    private Long coursId;
    private String coursName;

    // Recorder Details
    private Long recordedById;
    private String recordedByFullName;

    private LocalDate date;
    private String status;
    private String remarks;
}
