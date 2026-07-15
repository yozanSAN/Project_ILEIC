package com.ProjetILEIC.ILIEC.entity;

import com.ProjetILEIC.ILIEC.config.AuditRevisionListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.util.Date;

@Entity
@Table(name = "audit_revision")
@RevisionEntity(AuditRevisionListener.class) // Links this entity to our listener below
@Getter
@Setter
public class CustomRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "rev")
    private int id;

    @RevisionTimestamp
    @Column(name = "revtstmp")
    private long timestamp;

    @Column(name = "modified_by")
    private String modifiedBy; // 👈 This will store the username/email of the user who made the change

    @Transient
    public Date getRevisionDate() {
        return new Date(timestamp);
    }
}