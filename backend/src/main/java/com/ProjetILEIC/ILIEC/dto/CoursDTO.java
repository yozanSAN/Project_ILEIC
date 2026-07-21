package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursDTO {
    private Long id;
    private String code;
    private String name;
    private Long filiereId;
    private String filiereName;
    private Long centreId;
    private String centreName;
    private Integer yearLevel;
    private Integer semester;
    private Integer hoursTotal;
}