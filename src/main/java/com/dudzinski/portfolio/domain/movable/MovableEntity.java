package com.dudzinski.portfolio.domain.movable;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_movable")
public class MovableEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MovableType type;

    @Column(name = "productionYear")
    private int productionYear;

    @Column(name = "purchasePrice")
    private BigDecimal purchasePrice;

    @Column(name = "estimatedValue")
    private BigDecimal estimatedValue;
}