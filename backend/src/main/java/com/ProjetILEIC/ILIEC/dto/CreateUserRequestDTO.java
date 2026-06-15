package com.ProjetILEIC.ILIEC.dto;

import com.ProjetILEIC.ILIEC.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {
    private String fullName;
    private String email;
    private String password;
    private User.Role role;
    private Boolean isActive;
}
