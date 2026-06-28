package com.ProjetILEIC.ILIEC.dto;

import java.math.BigDecimal;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramRequestDTO {
    @NotBlank(message = "Program name is required")
    @Size(max = 150, message = "Program name must not exceed 150 characters")
    private String name;

    @NotNull(message = "Program duration in years is required")
    @Min(value = 1, message = "Duration must be at least 1 year")
    @Max(value = 5, message = "Duration cannot exceed 5 years")
    private Integer durationYears;

    @NotNull(message = "Monthly fee amount is required")
    @DecimalMin(value = "0.0", message = "Monthly fee cannot be negative")
    private BigDecimal monthlyFee;
}