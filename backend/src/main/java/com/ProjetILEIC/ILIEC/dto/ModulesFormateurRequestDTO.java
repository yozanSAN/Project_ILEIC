package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulesFormateurRequestDTO {

    @NotNull(message = "Formateur ID is required")
    private Long formateurId;

    @NotNull(message = "Cours ID is required")
    private Long coursId;

    @NotNull(message = "Academic year is required")
    @Min(value = 2020, message = "Academic year must be realistic")
    private Integer academicYear; // ex: 2026/2025

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 4, message = "Semester cannot exceed 4")
    private Integer semester;
}