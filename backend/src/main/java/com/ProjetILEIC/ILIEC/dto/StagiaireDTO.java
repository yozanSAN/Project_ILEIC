package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StagiaireDTO {

    private Long id;

    // From the embedded User — no passwordHash
    private Long userId;
    private String fullName;
    private String email;

    // Flattened from Centre and Filiere — names, not nested objects
    private Long centreId;
    private String centreName;

    private Long filiereId;
    private String filiereName;

    private String registrationNumber;
    private LocalDate birthDate;
    private String cin;
    private String phone;
    private String address;
    private LocalDate enrollmentDate;
    private String status;
}
