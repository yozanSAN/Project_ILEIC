package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.AdminAuditLogDTO;
import com.ProjetILEIC.ILIEC.dto.AdminAuditLogRequestDTO;
import com.ProjetILEIC.ILIEC.service.AdminAuditLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/audit-logs")
@RequiredArgsConstructor
public class AdminAuditLogController {

    private final AdminAuditLogService auditLogService;

    // Create a manual or specific log entry

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AdminAuditLogDTO> createLog(@Valid @RequestBody AdminAuditLogRequestDTO requestDTO) {
        AdminAuditLogDTO createdLog = auditLogService.createLog(requestDTO);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    // Get all system audit logs
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminAuditLogDTO>> getAllLogs() {
        return ResponseEntity.ok(auditLogService.getAllLogs());
    }

    // Filter logs by who performed the action
    @GetMapping("/admin/{adminId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminAuditLogDTO>> getLogsByAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(auditLogService.getLogsByAdminId(adminId));
    }

    // Filter logs by the type of thing changed (e.g., /api/admin/audit-logs/type/Centre)
    @GetMapping("/type/{entityType}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminAuditLogDTO>> getLogsByEntityType(@PathVariable String entityType) {
        return ResponseEntity.ok(auditLogService.getLogsByEntityType(entityType));
    }

    // Filter logs by specific actions (e.g., /api/admin/audit-logs/action/DEACTIVATED_USER)
    @GetMapping("/action/{action}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminAuditLogDTO>> getLogsByAction(@PathVariable String action) {
        return ResponseEntity.ok(auditLogService.getLogsByAction(action));
    }
}