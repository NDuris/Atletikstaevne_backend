package com.example.atletikstaevne_backend;

import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.repositorys.DeltagerRepository;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class DeltagerEntityListener {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DeltagerRepository deltagerRepository; // Replace with your repository class


    @Transactional
    @PreRemove
    public void onDeltagerPreRemove(Deltager deltager) {
        // Deaktiver foreign key checks
        entityManager.createNativeQuery("SET foreign_key_checks = 0").executeUpdate();
    }

    @Transactional
    @PostRemove
    public void onDeltagerPostRemove(Deltager deltager) {
        // Aktiver foreign key checks igen
        entityManager.createNativeQuery("SET foreign_key_checks = 1").executeUpdate();
    }
}
