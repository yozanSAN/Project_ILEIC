package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormateurRequestDTO {
    private Long userId;
    private String speciality;
    private LocalDate hireDate;
    private List<Long> centreIds;
}