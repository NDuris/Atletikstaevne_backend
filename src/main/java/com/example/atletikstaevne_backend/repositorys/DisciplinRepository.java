package com.example.atletikstaevne_backend.repositorys;

import com.example.atletikstaevne_backend.models.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinRepository extends JpaRepository<Disciplin, Long> {
}