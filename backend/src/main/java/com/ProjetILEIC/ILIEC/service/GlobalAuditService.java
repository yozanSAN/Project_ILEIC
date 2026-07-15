package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.AuditEntryDto;
import com.ProjetILEIC.ILIEC.entity.CustomRevisionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GlobalAuditService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Fetches the revision history of a specific entity by its ID.
     *
     * @param entityClass The class type of the entity (e.g., Stagiaire.class)
     * @param id The primary key of the record
     * @return List of AuditEntryDto showing Who, When, What, and the State.
     */
    @SuppressWarnings("unchecked")
    public List<AuditEntryDto> getEntityHistory(Class<?> entityClass, Object id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<AuditEntryDto> auditHistory = new ArrayList<>();

        // Create an Envers query to retrieve revisions, matching audit_revision details, and action types
        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.id().eq(id));

        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            Object entityState = row[0];                        // The state of the entity at this revision
            CustomRevisionEntity revision = (CustomRevisionEntity) row[1]; // Our Custom Revision Entity
            String actionType = row[2].toString();              // "ADD", "MOD", or "DEL"

            // Construct our standard DTO using getters
            AuditEntryDto dto = new AuditEntryDto(
                    revision.getId(),
                    revision.getRevisionDate().toString(),
                    actionType,
                    revision.getModifiedBy(), // 👈 Fetches the name of the user who made the change
                    entityState
            );

            auditHistory.add(dto);
        }

        return auditHistory;
    }
}