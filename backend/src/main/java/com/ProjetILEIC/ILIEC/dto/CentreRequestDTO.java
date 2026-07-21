package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentreRequestDTO {

    @NotBlank(message = "Centre name is required")
    @Size(max = 100, message = "Centre name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Centre address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phone;

    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;
}