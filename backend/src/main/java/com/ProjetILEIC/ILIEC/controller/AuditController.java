package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.AuditEntryDto;
import com.ProjetILEIC.ILIEC.service.GlobalAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/audit")
@PreAuthorize("hasAuthority('ADMIN')")
public class AuditController {

    @Autowired
    private GlobalAuditService auditService;

    // Change this to match your actual base package where entities are kept!
    private static final String ENTITY_PACKAGE_PREFIX = "com.ProjetILEIC.ILIEC.entity.";

    /**
     * Endpoint to fetch the history of a specific entity.
     * Example URL: GET http://localhost:8080/api/admin/audit/Stagiaire/12
     *
     * @param entityType The entity name (e.g., "Stagiaire" or "Filiere")
     * @param id The primary key of the record
     */
    @GetMapping("/{entityType}/{id}")
    public ResponseEntity<?> getHistory(@PathVariable String entityType, @PathVariable Object id) {
        try {
            // 1. Dynamically find the class type using reflection
            String className = ENTITY_PACKAGE_PREFIX + capitalize(entityType);
            Class<?> entityClass = Class.forName(className);

            // 2. Fetch the audit logs from the service
            List<AuditEntryDto> history = auditService.getEntityHistory(entityClass, convertId(id));

            return ResponseEntity.ok(history);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.badRequest().body("Entity type '" + entityType + "' not found in package " + ENTITY_PACKAGE_PREFIX);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving audit history: " + e.getMessage());
        }
    }

    // Helper to capitalize the entity name so "stagiaire" becomes "Stagiaire"
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Helper to parse ID as a number (since path variables default to Strings)
    private Object convertId(Object id) {
        try {
            return Long.parseLong(id.toString());
        } catch (NumberFormatException e) {
            return id; // If ID is a UUID or String-based key, keep it as is
        }
    }
}