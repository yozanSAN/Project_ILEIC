package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "formateur")
@Audited

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(length = 150)
    private String speciality;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToMany
    @JoinTable(
            name = "teacher_centres",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "centre_id")
    )
    private List<Centre> centres = new ArrayList<>();

}
