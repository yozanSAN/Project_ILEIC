package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereRequestDTO {
    @NotBlank(message = "Filiere name is required")
    @Size(max = 150, message = "Filiere name must not exceed 150 characters")
    private String name;

    @NotNull(message = "Centre ID reference is required")
    private Long centreId;

    @NotNull(message = "Program ID reference is required")
    private Long programId;
}