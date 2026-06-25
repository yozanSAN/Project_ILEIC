package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploiDeTempRequestDTO {

    @NotNull(message = "Centre ID is required")
    private Long centreId;

    @NotNull(message = "Filiere ID is required")
    private Long filiereId;

    @NotNull(message = "Cours ID is required")
    private Long coursId;

    @NotNull(message = "Formateur ID is required")
    private Long formateurId;

    @NotBlank(message = "Day of week is required")
    @Size(max = 20, message = "Day of week must not exceed 20 characters")
    private String dayOfWeek;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @Size(max = 50, message = "Room name must not exceed 50 characters")
    private String room;
}