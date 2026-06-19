package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereRequestDTO {
    private String name;
    private Long centreId;
    private Long programId;
}