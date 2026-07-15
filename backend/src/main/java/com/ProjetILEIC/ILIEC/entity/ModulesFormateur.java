package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "teacher_subjects")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ModulesFormateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Cours cours;

    @Column(name = "academic_year")
    private Integer academicYear;

    private Integer semester;
}