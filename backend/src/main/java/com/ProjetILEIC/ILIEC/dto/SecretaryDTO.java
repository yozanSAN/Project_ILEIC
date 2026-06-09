package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecretaryDTO {

    private Long id;

    // From the embedded User — no passwordHash
    private Long userId;
    private String fullName;
    private String email;

    private Long centreId;
    private String centreName;
}
