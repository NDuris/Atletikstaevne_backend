package com.example.atletikstaevne_backend.DTO;

import com.example.atletikstaevne_backend.models.ResultatType;

public class DisciplinDTO {
    private Long id;
    private String navn;
    private ResultatType resultatType;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public ResultatType getResultatType() {
        return resultatType;
    }

    public void setResultatType(ResultatType resultatType) {
        this.resultatType = resultatType;
    }
}
