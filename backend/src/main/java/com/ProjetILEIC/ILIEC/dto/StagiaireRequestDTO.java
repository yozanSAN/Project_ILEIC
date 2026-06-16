package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StagiaireRequestDTO {
    private String registrationNumber;
    private LocalDate birthDate;
    private String cin;
    private String phone;
    private String address;
    private LocalDate enrollmentDate;
    private String status;

    // Relation IDs
    private Long userId;
    private Long centreId;
    private Long filiereId;
}
