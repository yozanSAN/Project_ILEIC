package com.ProjetILEIC.ILIEC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminAuditLogRequestDTO {

    @NotNull(message = "Admin User ID is required")
    private Long adminUserId;

    @NotBlank(message = "Action cannot be blank")
    @Size(max = 100, message = "Action description must not exceed 100 characters")
    private String action;

    @NotBlank(message = "Entity type cannot be blank")
    @Size(max = 50, message = "Entity type must not exceed 50 characters")
    private String entityType;

    private Long entityId;

    private String details;
}