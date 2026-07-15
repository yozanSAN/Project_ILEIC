package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "controle")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Controle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cour_id", nullable = false)
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 50)
    private String type;

    @Column(name = "exam_date")
    private LocalDate examDate;

    @Column(precision = 5, scale = 2)
    private BigDecimal coefficient;

    @Column(length = 50)
    private String status;
}
