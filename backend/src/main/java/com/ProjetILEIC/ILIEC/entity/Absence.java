package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "absence")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id", nullable = false)
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "cour_id", nullable = false)
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 50)
    private String status;

    @Column(length = 255)
    private String remarks;
}