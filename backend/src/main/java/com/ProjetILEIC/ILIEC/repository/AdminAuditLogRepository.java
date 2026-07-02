package com.ProjetILEIC.ILIEC.repository;

import com.ProjetILEIC.ILIEC.entity.AdminAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAuditLogRepository extends JpaRepository<AdminAuditLog, Long> {

    // Fetch logs filtered by a specific entity type (e.g., "Centre", "User")
    List<AdminAuditLog> findByEntityType(String entityType);

    // Fetch logs for a specific admin user
    List<AdminAuditLog> findByAdminUserId(Long adminUserId);

    // Fetch logs by action name (e.g., "DEACTIVATED_USER")
    List<AdminAuditLog> findByAction(String action);
}