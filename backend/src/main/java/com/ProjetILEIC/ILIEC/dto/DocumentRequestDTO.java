package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {

    @NotNull(message = "Stagiaire ID is required")
    private Long stagiaireId;

    @NotNull(message = "Uploader User ID is required")
    private Long uploadedById;

    @NotBlank(message = "Document type is required")
    @Size(max = 100, message = "Document type must not exceed 100 characters")
    private String documentType; // ex: "CIN", "BAC_DIPLOMA", "PHOTO"

    @NotBlank(message = "File URL is required")
    @Size(max = 255, message = "File URL path must not exceed 255 characters")
    private String fileUrl;
}