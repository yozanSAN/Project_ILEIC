package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntryDto {
    private Number revisionId;
    private String revisionDate;
    private String actionType;     // "ADD", "MOD", "DEL"
    private String modifiedBy;     // 👈 "ms naima secretary", "profe rachid", etc.
    private Object entityState;    // The actual entity data (what they did)
}