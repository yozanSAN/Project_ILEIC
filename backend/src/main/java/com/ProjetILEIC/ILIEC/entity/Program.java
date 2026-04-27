package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.*;

@Entity
@Table(name = "program")

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
}
