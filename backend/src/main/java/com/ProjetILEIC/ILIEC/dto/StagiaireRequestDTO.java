package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StagiaireRequestDTO {
    @NotBlank(message = "Registration number is required")
    @Size(max = 50, message = "Registration number must not exceed 50 characters")
    private String registrationNumber;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be a valid date in the past")
    private LocalDate birthDate;

    @NotBlank(message = "CIN is required")
    @Size(max = 20, message = "CIN must not exceed 20 characters")
    private String cin;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotNull(message = "Enrollment date is required")
    @PastOrPresent(message = "Enrollment date cannot be in the future")
    private LocalDate enrollmentDate;

    @NotBlank(message = "Status is required") // e.g., ACTIVE, SUSPENDED, GRADUATED
    @Size(max = 30, message = "Status text is too long")
    private String status;

    // Relation IDs
    @NotNull(message = "User ID reference is required")
    private Long userId;

    @NotNull(message = "Centre ID reference is required")
    private Long centreId;

    @NotNull(message = "Filiere ID reference is required")
    private Long filiereId;
}
