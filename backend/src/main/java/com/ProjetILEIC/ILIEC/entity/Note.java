package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "note")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id", nullable = false)
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "controle_id", nullable = false)
    private Controle controle;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;

    @Column(name = "note_value", precision = 5, scale = 2)
    private BigDecimal gradeValue;

    @Column(length = 255)
    private String remarks;
}
