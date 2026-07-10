package com.ProjetILEIC.ILIEC.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequestDTO {
    @NotBlank(message = "Refresh token cannot be blank")
    private String refreshToken;
}