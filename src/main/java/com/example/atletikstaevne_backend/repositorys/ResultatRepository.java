package com.example.atletikstaevne_backend.repositorys;

import com.example.atletikstaevne_backend.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {

    @Query("SELECT r FROM Resultat r WHERE r.deltager.deltagerId = :deltagerId")
    List<Resultat> findResultaterForDeltager(@Param("deltagerId") Long deltagerId);

    @Query("SELECT r FROM Resultat r JOIN FETCH r.deltager JOIN FETCH r.disciplin")
    List<Resultat> findAllWithDeltagerAndDisciplin();

    @Query("SELECT r, r.disciplin FROM Resultat r JOIN FETCH r.deltager JOIN FETCH r.disciplin WHERE r.deltager.deltagerId = :deltagerId")
    List<Resultat> findResultaterForDeltagerWithDisciplin(@Param("deltagerId") Long deltagerId);
}
