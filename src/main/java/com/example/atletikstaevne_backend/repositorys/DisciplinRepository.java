package com.example.atletikstaevne_backend.repositorys;

import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.models.Disciplin;
import com.example.atletikstaevne_backend.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisciplinRepository extends JpaRepository<Disciplin, Long> {

    @Query("SELECT DISTINCT r.deltager FROM Resultat r WHERE r.disciplin.disciplinId = :disciplinId")
    List<Deltager> findDeltagereByDisciplinId(Long disciplinId);

    @Query("SELECT r FROM Resultat r JOIN r.disciplin d WHERE d.disciplinId = :disciplinId")
    List<Resultat> findResultaterByDisciplinId(Long disciplinId);
}