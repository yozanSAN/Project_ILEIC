package com.ProjetILEIC.ILIEC.entity;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "secretary")
@Audited

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
    @JoinColumn(name = "centre_id", unique = true, nullable = false)
    private Centre centre;
}
