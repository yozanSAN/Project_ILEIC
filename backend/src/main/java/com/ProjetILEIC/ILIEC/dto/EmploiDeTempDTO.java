package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploiDeTempDTO {

    private Long id;

    // Linked Entity Details
    private Long centreId;
    private String centreName;

    private Long filiereId;
    private String filiereName;

    private Long coursId;
    private String coursName;

    private Long formateurId;
    private String formateurFullName;

    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
}