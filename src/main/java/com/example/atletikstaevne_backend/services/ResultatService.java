package com.example.atletikstaevne_backend.services;


import com.example.atletikstaevne_backend.models.Resultat;
import com.example.atletikstaevne_backend.repositorys.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;

    public List<Resultat> findAllResults() {
        return resultatRepository.findAll();
    }

    public List<Resultat> findAll() {
        return resultatRepository.findAllWithDeltagerAndDisciplin();
    }

    public Optional<Resultat> findById(Long id) {
        return resultatRepository.findById(id);
    }

    public Resultat save(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public void deleteById(Long id) {
        resultatRepository.deleteById(id);
    }
//
//    public List<Resultat> findResultaterForDeltager(Long deltagerId) {
//        return resultatRepository.findResultaterForDeltager(deltagerId);
//    }
    public List<Resultat> findResultaterForDeltager(Long deltagerId) {
        return resultatRepository.findResultaterForDeltagerWithDisciplin(deltagerId);
    }
}
