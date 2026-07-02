package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.AdminAuditLog;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.AdminAuditLogRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import com.ProjetILEIC.ILIEC.dto.AdminAuditLogDTO;
import com.ProjetILEIC.ILIEC.dto.AdminAuditLogRequestDTO;
import com.ProjetILEIC.ILIEC.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminAuditLogService {

    private final AdminAuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    // --- WRITE ACTIONS ---
    @Transactional
    public AdminAuditLogDTO createLog(AdminAuditLogRequestDTO requestDTO) {
        User admin = userRepository.findById(requestDTO.getAdminUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found with id: " + requestDTO.getAdminUserId()));

        // Mapping Request DTO -> Entity using the @Builder
        AdminAuditLog log = AdminAuditLog.builder()
                .adminUser(admin)
                .action(requestDTO.getAction())
                .entityType(requestDTO.getEntityType())
                .entityId(requestDTO.getEntityId())
                .details(requestDTO.getDetails())
                .build();

        AdminAuditLog savedLog = auditLogRepository.save(log);
        return mapToDTO(savedLog);
    }

    // --- READ ACTIONS ---
    public List<AdminAuditLogDTO> getAllLogs() {
        return auditLogRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AdminAuditLogDTO> getLogsByEntityType(String entityType) {
        return auditLogRepository.findByEntityType(entityType).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AdminAuditLogDTO> getLogsByAdminId(Long adminId) {
        return auditLogRepository.findByAdminUserId(adminId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AdminAuditLogDTO> getLogsByAction(String action) {
        return auditLogRepository.findByAction(action).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // --- INTERNAL HELPER MAPPER ---
    private AdminAuditLogDTO mapToDTO(AdminAuditLog log) {
        return AdminAuditLogDTO.builder()
                .id(log.getId())
                .adminUserId(log.getAdminUser().getId())
                .adminFullName(log.getAdminUser().getFullName())
                .action(log.getAction())
                .entityType(log.getEntityType())
                .entityId(log.getEntityId())
                .details(log.getDetails())
                .performedAt(log.getPerformedAt())
                .build();
    }
}
