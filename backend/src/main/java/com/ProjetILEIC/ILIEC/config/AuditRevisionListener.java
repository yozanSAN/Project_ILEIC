package com.ProjetILEIC.ILIEC.config;

import com.ProjetILEIC.ILIEC.entity.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;

        // 1. Ask Spring Security for the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            // Store the logged-in user's username/email
            customRevisionEntity.setModifiedBy(authentication.getName());
        } else {
            // Fallback for system-level actions, initial seeding, or unauthenticated API calls
            customRevisionEntity.setModifiedBy("SYSTEM");
        }
    }
}