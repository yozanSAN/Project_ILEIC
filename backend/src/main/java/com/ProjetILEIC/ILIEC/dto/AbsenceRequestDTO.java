package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceRequestDTO {
    @NotNull(message = "Stagiaire ID is required")
    private Long stagiaireId;

    @NotNull(message = "Cours ID is required")
    private Long coursId;

    @NotNull(message = "Recorded By User ID is required")
    private Long recordedById;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must not exceed 50 characters")
    private String status; // EX: "UNJUSTIFIED", "JUSTIFIED"

    @Size(max = 255, message = "Remarks must not exceed 255 characters")
    private String remarks;
}