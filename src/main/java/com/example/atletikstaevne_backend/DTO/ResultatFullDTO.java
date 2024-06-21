package com.example.atletikstaevne_backend.DTO;

import com.example.atletikstaevne_backend.models.ResultatType;

import java.time.LocalDate;

public class ResultatFullDTO {
    private Long resultatId;
    private LocalDate dato;
    private ResultatType resultatType;
    private String resultatvaerdi;

    private Long deltagerId;
    private String deltagerNavn;
    private String deltagerKoen;
    private int deltagerAlder;

    private Long disciplinId;
    private String disciplinNavn;

    // Constructor, getters og setters

    public Long getResultatId() {
        return resultatId;
    }

    public void setResultatId(Long resultatId) {
        this.resultatId = resultatId;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public ResultatType getResultatType() {
        return resultatType;
    }

    public void setResultatType(ResultatType resultatType) {
        this.resultatType = resultatType;
    }

    public String getResultatvaerdi() {
        return resultatvaerdi;
    }

    public void setResultatvaerdi(String resultatvaerdi) {
        this.resultatvaerdi = resultatvaerdi;
    }

    public Long getDeltagerId() {
        return deltagerId;
    }

    public void setDeltagerId(Long deltagerId) {
        this.deltagerId = deltagerId;
    }

    public String getDeltagerNavn() {
        return deltagerNavn;
    }

    public void setDeltagerNavn(String deltagerNavn) {
        this.deltagerNavn = deltagerNavn;
    }

    public String getDeltagerKoen() {
        return deltagerKoen;
    }

    public void setDeltagerKoen(String deltagerKoen) {
        this.deltagerKoen = deltagerKoen;
    }

    public int getDeltagerAlder() {
        return deltagerAlder;
    }

    public void setDeltagerAlder(int deltagerAlder) {
        this.deltagerAlder = deltagerAlder;
    }

    public Long getDisciplinId() {
        return disciplinId;
    }

    public void setDisciplinId(Long disciplinId) {
        this.disciplinId = disciplinId;
    }

    public String getDisciplinNavn() {
        return disciplinNavn;
    }

    public void setDisciplinNavn(String disciplinNavn) {
        this.disciplinNavn = disciplinNavn;
    }
}