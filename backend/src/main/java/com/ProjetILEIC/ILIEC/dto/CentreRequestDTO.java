package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentreRequestDTO {
    private String name;
    private String address;
    private String phone;
    private String email;
}