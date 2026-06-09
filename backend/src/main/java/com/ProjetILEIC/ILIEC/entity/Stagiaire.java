package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.*;

@Entity
@Table(name = "stagiaire")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "centre_id", nullable = false)
    private Centre centre;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    @Column(name = "registration_number", unique = true, nullable = false, length = 100)
    private String registrationNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 50)
    private String cin;

    @Column(length = 30)
    private String phone;

    @Column(length = 255)
    private String address;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(length = 50)
    private String status;
}
