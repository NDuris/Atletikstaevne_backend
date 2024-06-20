package com.example.atletikstaevne_backend.DTO;

import com.example.atletikstaevne_backend.models.ResultatType;

import java.time.LocalDate;

public class ResultatDTO {
    private Long id;
    private LocalDate dato;
    private ResultatType resultatType;
    private String resultatvaerdi;
    private Long deltagerId;
    private Long disciplinId;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDisciplinId() {
        return disciplinId;
    }

    public void setDisciplinId(Long disciplinId) {
        this.disciplinId = disciplinId;
    }
}
