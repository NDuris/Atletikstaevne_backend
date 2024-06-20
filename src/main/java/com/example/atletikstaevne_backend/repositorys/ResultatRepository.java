package com.example.atletikstaevne_backend.repositorys;

import com.example.atletikstaevne_backend.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {
}