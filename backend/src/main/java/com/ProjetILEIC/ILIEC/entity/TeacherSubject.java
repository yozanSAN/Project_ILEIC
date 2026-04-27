package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher_subjects")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TeacherSubject {

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