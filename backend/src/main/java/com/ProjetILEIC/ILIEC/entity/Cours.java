package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "cours")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String code;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    @Column(name = "year_level")
    private Integer yearLevel;

    private Integer semester;

    @Column(name = "hours_total")
    private Integer hoursTotal;
}
