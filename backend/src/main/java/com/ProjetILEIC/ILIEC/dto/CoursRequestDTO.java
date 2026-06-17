// CoursRequestDTO.java
package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursRequestDTO {
    private String name;
    private String code;
    private Long filiereId;
    private Integer yearLevel;
    private Integer semester;
    private Integer hoursTotal;
}