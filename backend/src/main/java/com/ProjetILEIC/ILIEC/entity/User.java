package com.ProjetILEIC.ILIEC.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.*;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    // ==========================================
    // SPRING SECURITY REQUIRED OVERRIDES
    // ==========================================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This takes your database Role Enum and feeds it to Spring Security
        return List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getPassword() {
        // Maps to your passwordHash field in the DB
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        // Maps to your email field since you use email for logging in
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Set to true so account access doesn't expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Set to true so accounts don't lock up automatically
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Set to true so passwords don't force-expire
    }

    @Override
    public boolean isEnabled() {
        // Ties account status cleanly to your DB boolean!
        return this.isActive != null ? this.isActive : true;
    }

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
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES) //  Handles Case-insensitivety
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}