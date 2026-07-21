package com.ProjetILEIC.ILIEC.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentreDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Boolean isActive;
}