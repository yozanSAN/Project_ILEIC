package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import java.time.LocalTime;

import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "emploiDeTemp")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmploiDeTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "centre_id", nullable = false)
    private Centre centre;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Formateur formateur;

    @Column(name = "day_of_week", length = 20)
    private String dayOfWeek;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(length = 50)
    private String room;
}
