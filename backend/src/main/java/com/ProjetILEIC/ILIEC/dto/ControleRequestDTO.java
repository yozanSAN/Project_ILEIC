package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleRequestDTO {
    @NotNull(message = "Cours ID reference is required")
    private Long coursId;

    @NotNull(message = "Filiere ID reference is required")
    private Long filiereId;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Exam type is required") // e.g., CC, EFM
    private String type;

    @NotNull(message = "Exam date is required")
    @FutureOrPresent(message = "Exam date cannot be in the past")
    private LocalDate examDate;

    @NotNull(message = "Coefficient value is required")
    @DecimalMin(value = "0.1", message = "Coefficient must be greater than 0")
    private BigDecimal coefficient;

    @NotBlank(message = "Status is required")
    private String status;
}