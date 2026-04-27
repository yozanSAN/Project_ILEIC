package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "filiere")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "centre_id", nullable = false)
    private Centre centre;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;
}
