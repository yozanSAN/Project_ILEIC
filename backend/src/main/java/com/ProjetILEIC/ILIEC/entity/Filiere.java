package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "filiere")
@Audited

@SQLDelete(sql = "UPDATE filiere SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")

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

    @Column(nullable = false)
    private boolean deleted = false;
}
