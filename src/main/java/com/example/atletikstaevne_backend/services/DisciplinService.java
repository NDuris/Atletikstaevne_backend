package com.example.atletikstaevne_backend.services;

import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.models.Disciplin;
import com.example.atletikstaevne_backend.models.Resultat;
import com.example.atletikstaevne_backend.repositorys.DisciplinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinService {
    @Autowired
    private DisciplinRepository disciplinRepository;

    public List<Disciplin> findAll() {
        return disciplinRepository.findAll();
    }

    public Optional<Disciplin> findById(Long id) {
        return disciplinRepository.findById(id);
    }

    public List<Deltager> findDeltagereByDisciplinId(Long disciplinId) {
        return disciplinRepository.findDeltagereByDisciplinId(disciplinId);
    }

    public List<Resultat> findResultaterByDisciplinId(Long disciplinId) {
        return disciplinRepository.findResultaterByDisciplinId(disciplinId);
    }

    public Disciplin save(Disciplin disciplin) {
        return disciplinRepository.save(disciplin);
    }

    public void deleteById(Long id) {
        disciplinRepository.deleteById(id);
    }
}