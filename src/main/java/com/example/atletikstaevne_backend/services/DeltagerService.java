package com.example.atletikstaevne_backend.services;

import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.repositorys.DeltagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeltagerService {
    @Autowired
    private DeltagerRepository deltagerRepository;

    public List<Deltager> findAll() {
        return deltagerRepository.findAll();
    }

    public Optional<Deltager> findById(Long id) {
        return deltagerRepository.findById(id);
    }

    public Deltager save(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }

    public void deleteById(Long id) {
        deltagerRepository.deleteById(id);
    }

    public List<Deltager> findByNavn(String navn) {
        return deltagerRepository.findByNavnContaining(navn);
    }
}