package com.example.demo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "chambres")
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal prix;

    @Column(nullable = false)
    private Boolean disponible = true;


    public Chambre() {
    }

    public Long getId() {
        return id;
    }

    public Chambre setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Chambre setType(String type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Chambre setPrix(BigDecimal prix) {
        this.prix = prix;
        return this;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Chambre setDisponible(Boolean disponible) {
        this.disponible = disponible;
        return this;
    }
}