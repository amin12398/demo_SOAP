package com.example.demo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "chambre_id", nullable = false)
    private Chambre chambre;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(length = 500)
    private String preferences;

    // Constructeurs
    public Reservation() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public Reservation setId(Long id) {
        this.id = id;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Reservation setClient(Client client) {
        this.client = client;
        return this;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public Reservation setChambre(Chambre chambre) {
        this.chambre = chambre;
        return this;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Reservation setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Reservation setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public String getPreferences() {
        return preferences;
    }

    public Reservation setPreferences(String preferences) {
        this.preferences = preferences;
        return this;
    }
}