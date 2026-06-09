package com.ProjetILEIC.ILIEC.dto;

import com.ProjetILEIC.ILIEC.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private User.Role role;

    public UserDTO(Long id, String fullName, String email, User.Role role, Boolean isActive, LocalDateTime createdAt) {
    }
}