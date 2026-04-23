package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "centre")
@Data
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centre")
    private Integer idCentre;

    @Column(name = "nom_centre", length = 150)
    private String nomCentre;
}