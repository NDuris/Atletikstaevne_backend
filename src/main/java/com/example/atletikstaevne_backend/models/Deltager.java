package com.example.atletikstaevne_backend.models;

import com.example.atletikstaevne_backend.DeltagerEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.Set;


import java.util.HashSet;

@Entity
//@EntityListeners(DeltagerEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Deltager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deltagerId;
    @Column(nullable = false)
    private String navn;
    @Column(nullable = false)
    private String koen;
    @Column(nullable = false)
    private int alder;
    @Column(nullable = false)
    private String klub;

    @OneToMany(mappedBy = "deltager", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resultat> resultater = new HashSet<>();

    // Getters and setters

    public Long getDeltagerId() {
        return deltagerId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getKoen() {
        return koen;
    }

    public void setKoen(String koen) {
        this.koen = koen;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    public String getKlub() {
        return klub;
    }

    public void setKlub(String klub) {
        this.klub = klub;
    }
}