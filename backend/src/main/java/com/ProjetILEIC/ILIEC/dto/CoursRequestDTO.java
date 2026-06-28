// CoursRequestDTO.java
package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursRequestDTO {
    @NotBlank(message = "Course name is required")
    @Size(max = 150, message = "Course name must not exceed 150 characters")
    private String name;

    @NotBlank(message = "Course code is required")
    private String code;

    @NotNull(message = "Filiere ID reference is required")
    private Long filiereId;

    @NotNull(message = "Year level is required")
    @Min(value = 1, message = "Year level must be at least 1")
    private Integer yearLevel;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    private Integer semester;

    @NotNull(message = "Total hours are required")
    @Min(value = 1, message = "Total hours must be at least 1 hour")
    private Integer hoursTotal;
}