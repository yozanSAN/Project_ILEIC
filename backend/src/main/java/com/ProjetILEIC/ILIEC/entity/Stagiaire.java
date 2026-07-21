package com.ProjetILEIC.ILIEC.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "stagiaire")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotAudited
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centre_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Centre centre;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Filiere filiere;

    @Column(name = "registration_number", unique = true, nullable = false, length = 100)
    private String registrationNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 50)
    private String cin;

    @Column(length = 255)
    private String address;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(length = 50)
    private String status;
}
