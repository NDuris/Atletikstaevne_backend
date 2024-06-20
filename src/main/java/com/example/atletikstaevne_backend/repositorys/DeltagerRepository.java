package com.example.atletikstaevne_backend.repositorys;

import com.example.atletikstaevne_backend.models.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeltagerRepository extends JpaRepository<Deltager, Long> {
    List<Deltager> findByNavnContaining(String navn);
}