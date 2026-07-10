package com.ProjetILEIC.ILIEC.dto;

import com.ProjetILEIC.ILIEC.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String accessToken;
    private String refreshToken;
    private String email;
    private User.Role role;
}
