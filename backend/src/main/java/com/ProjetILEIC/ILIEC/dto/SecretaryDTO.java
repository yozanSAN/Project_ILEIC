package com.ProjetILEIC.ILIEC.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretaryDTO {

    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String phone;

    private Long centreId;
    private String centreName;
}
