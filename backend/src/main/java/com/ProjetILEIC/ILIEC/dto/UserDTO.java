package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import com.ProjetILEIC.ILIEC.entity.User;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private User.Role role;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
