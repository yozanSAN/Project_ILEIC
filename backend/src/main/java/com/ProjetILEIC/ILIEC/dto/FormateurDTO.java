package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormateurDTO {

    private Long id;

    // From the embedded User — no passwordHash
    private Long userId;
    private String fullName;
    private String email;

    private String speciality;
    private LocalDate hireDate;

    // Just the names of centres they're assigned to — no nested objects
    private List<String> centreNames;
}
