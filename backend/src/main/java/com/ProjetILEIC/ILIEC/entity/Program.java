package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "program")
@Audited

@SQLDelete(sql = "UPDATE program SET deleted = true WHERE id = ?") // Intercepts repository.delete() calls
@Where(clause = "deleted = false") // Automatically filters out soft-deleted records on fetches

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "duration_years", nullable = false)
    private Integer durationYears;

    @Column(name = "monthly_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyFee;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleted = false;
}
