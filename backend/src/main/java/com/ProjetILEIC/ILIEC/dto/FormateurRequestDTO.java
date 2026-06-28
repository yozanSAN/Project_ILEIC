package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormateurRequestDTO {
    @NotNull(message = "User ID reference is required")
    private Long userId;

    @NotBlank(message = "Speciality is required")
    @Size(max = 100, message = "Speciality name must not exceed 100 characters")
    private String speciality;

    @NotNull(message = "Hire date is required")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @NotEmpty(message = "Stagiaire must be assigned to one center")
    private List<Long> centreIds;
}