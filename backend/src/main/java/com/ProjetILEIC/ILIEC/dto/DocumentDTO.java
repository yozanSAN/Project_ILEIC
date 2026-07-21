package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long id;

    private Long stagiaireId;
    private String stagiaireFullName;

    private Long uploadedById;
    private String uploadedByFullName;

    private String documentType;
    private String fileUrl;
    private String status;
    private LocalDateTime uploadedAt;
}