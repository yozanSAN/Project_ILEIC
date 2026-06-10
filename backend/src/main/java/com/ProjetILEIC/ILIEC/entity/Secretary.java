package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "secretary")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Secretary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "centre_id", nullable = false)
    private Centre centre;
}
