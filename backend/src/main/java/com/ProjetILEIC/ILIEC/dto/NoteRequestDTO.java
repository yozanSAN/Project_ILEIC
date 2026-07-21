package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequestDTO {
    @NotNull(message = "Stagiaire ID reference is required")
    private Long stagiaireId;

    @NotNull(message = "Controle ID reference is required")
    private Long controleId;

    @NotNull(message = "ID of the person recording the grade is required")
    private Long recordedById;

    @NotNull(message = "Grade value is required")
    @DecimalMin(value = "0.0", message = "Grade cannot be lower than 0.0")
    @DecimalMax(value = "20.0", message = "Grade cannot be higher than 20.0")
    private BigDecimal gradeValue;

    @Size(max = 255, message = "Remarks must not exceed 255 characters")
    private String remarks;
}