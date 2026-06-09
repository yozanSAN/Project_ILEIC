package com.ProjetILEIC.ILIEC.dto;

import com.ProjetILEIC.ILIEC.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String email;
    private User.Role role;
}
