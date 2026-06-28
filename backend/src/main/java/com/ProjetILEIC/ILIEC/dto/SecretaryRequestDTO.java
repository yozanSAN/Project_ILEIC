package com.ProjetILEIC.ILIEC.dto;

import lombok.*;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretaryRequestDTO {

    @NotNull(message = "User ID reference is required")
    private Long userId;
    @NotNull(message = "Centre ID reference is required")
    private Long centreId;
}