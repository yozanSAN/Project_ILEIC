package com.ProjetILEIC.ILIEC.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereDTO {
    private Long id;
    private String name;
    private CentreDTO centre;
    private ProgramDTO program;
}