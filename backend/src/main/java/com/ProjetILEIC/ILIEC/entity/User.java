package com.ProjetILEIC.ILIEC.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "users")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    //ROLE ENUMS
    public enum Role {
        ADMIN,
        SECRETAIRE,
        FORMATEUR,
        STAGIAIRE
    }

    //USER TABLE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore // This prevents the field from ever being serialized to JSON
    @NotAudited
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES) //  Handles Case-insensitivety
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}