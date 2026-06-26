package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulesFormateurDTO {

    private Long id;

    // Formateur Details
    private Long formateurId;
    private String formateurFullName;

    // Cours Details
    private Long coursId;
    private String coursName;

    private Integer academicYear;
    private Integer semester;
}