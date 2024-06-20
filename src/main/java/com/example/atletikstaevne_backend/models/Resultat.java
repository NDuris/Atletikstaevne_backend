package com.example.atletikstaevne_backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultatId;
    @Enumerated(EnumType.STRING)
    private ResultatType resultatType;
    private LocalDate dato;
    private String resultatvaerdi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deltager_id")
    private Deltager deltager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;

    // Getters and setters


    public void setResultatId(Long resultatId) {
        this.resultatId = resultatId;
    }

    public Long getResultatId() {
        return resultatId;
    }

    public ResultatType getResultatType() {
        return resultatType;
    }

    public void setResultatType(ResultatType resultatType) {
        this.resultatType = resultatType;
    }
    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public String getResultatvaerdi() {
        return resultatvaerdi;
    }

    public void setResultatvaerdi(String resultatvaerdi) {
        this.resultatvaerdi = resultatvaerdi;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public void setDeltager(Deltager deltager) {
        this.deltager = deltager;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }

    public void setDisciplin(Disciplin disciplin) {
        this.disciplin = disciplin;
    }
}
