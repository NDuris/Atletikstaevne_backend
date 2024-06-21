package com.example.atletikstaevne_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disciplinId;

    @Column(nullable = false)
    private String navn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResultatType resultatType;

    @OneToMany(mappedBy = "disciplin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resultat> resultater = new HashSet<>();

    // Getters and setters

    public Long getDisciplinId() {
        return disciplinId;
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