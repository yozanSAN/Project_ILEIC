package com.ProjetILEIC.ILIEC.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminAuditLogDTO {
    private Long id;
    private Long adminUserId;
    private String adminFullName;
    private String action;
    private String entityType;
    private Long entityId;
    private String details;
    private LocalDateTime performedAt;
}
