package com.example.atletikstaevne_backend;

import com.example.atletikstaevne_backend.DTO.ResultatDTO;
import com.example.atletikstaevne_backend.models.ResultatType;
import com.example.atletikstaevne_backend.repositorys.DeltagerRepository;
import com.example.atletikstaevne_backend.repositorys.DisciplinRepository;
import com.example.atletikstaevne_backend.repositorys.ResultatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ResultatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON

    @BeforeEach
    public void setup() {
        resultatRepository.deleteAll();
        deltagerRepository.deleteAll();
        disciplinRepository.deleteAll();
    }

    @Test
    public void testGetAllResultater() throws Exception {
        // Setup
        com.example.atletikstaevne_backend.models.Deltager deltager = new com.example.atletikstaevne_backend.models.Deltager();
        deltager.setNavn("John Doe");
        deltager.setKoen("Male");
        deltager.setAlder(30);
        deltager.setKlub("ABC Club");
        deltagerRepository.save(deltager);

        com.example.atletikstaevne_backend.models.Disciplin disciplin = new com.example.atletikstaevne_backend.models.Disciplin();
        disciplin.setNavn("100m løb");
        disciplin.setResultatType(ResultatType.TID);
        disciplinRepository.save(disciplin);

        com.example.atletikstaevne_backend.models.Resultat resultat = new com.example.atletikstaevne_backend.models.Resultat();
        resultat.setResultatType(ResultatType.TID);
        resultat.setDato(LocalDate.now());
        resultat.setResultatvaerdi("10.00");
        resultat.setDeltager(deltager);
        resultat.setDisciplin(disciplin);
        resultatRepository.save(resultat);

        // Perform and verify
        mockMvc.perform(get("/api/resultater"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].resultatvaerdi", is("10.00")));
    }

    @Test
    public void testCreateResultat() throws Exception {
        // Setup
        com.example.atletikstaevne_backend.models.Deltager deltager = new com.example.atletikstaevne_backend.models.Deltager();
        deltager.setNavn("John Doe");
        deltager.setKoen("Male");
        deltager.setAlder(30);
        deltager.setKlub("ABC Club");
        deltagerRepository.save(deltager);

        com.example.atletikstaevne_backend.models.Disciplin disciplin = new com.example.atletikstaevne_backend.models.Disciplin();
        disciplin.setNavn("100m løb");
        disciplin.setResultatType(ResultatType.TID);
        disciplinRepository.save(disciplin);

        ResultatDTO resultatDTO = new ResultatDTO();
        resultatDTO.setResultatType(ResultatType.TID);
        resultatDTO.setDato(LocalDate.now());
        resultatDTO.setResultatvaerdi("10.00");
        resultatDTO.setDeltagerId(deltager.getDeltagerId());
        resultatDTO.setDisciplinId(disciplin.getDisciplinId());

        // Perform and verify
        mockMvc.perform(post("/api/resultater")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultatDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultatvaerdi", is("10.00")));
    }

    @Test
    public void testUpdateResultat() throws Exception {
        // Setup
        com.example.atletikstaevne_backend.models.Deltager deltager = new com.example.atletikstaevne_backend.models.Deltager();
        deltager.setNavn("John Doe");
        deltager.setKoen("Male");
        deltager.setAlder(30);
        deltager.setKlub("ABC Club");
        deltagerRepository.save(deltager);

        com.example.atletikstaevne_backend.models.Disciplin disciplin = new com.example.atletikstaevne_backend.models.Disciplin();
        disciplin.setNavn("100m løb");
        disciplin.setResultatType(ResultatType.TID);
        disciplinRepository.save(disciplin);

        com.example.atletikstaevne_backend.models.Resultat resultat = new com.example.atletikstaevne_backend.models.Resultat();
        resultat.setResultatType(ResultatType.TID);
        resultat.setDato(LocalDate.now());
        resultat.setResultatvaerdi("10.00");
        resultat.setDeltager(deltager);
        resultat.setDisciplin(disciplin);
        resultatRepository.save(resultat);

        ResultatDTO updatedResultatDTO = new ResultatDTO();
        updatedResultatDTO.setResultatType(ResultatType.TID);
        updatedResultatDTO.setDato(LocalDate.now());
        updatedResultatDTO.setResultatvaerdi("9.50");
        updatedResultatDTO.setDeltagerId(deltager.getDeltagerId());
        updatedResultatDTO.setDisciplinId(disciplin.getDisciplinId());

        // Perform and verify
        mockMvc.perform(put("/api/resultater/" + resultat.getResultatId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedResultatDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultatvaerdi", is("9.50")));
    }

    @Test
    public void testDeleteResultat() throws Exception {
        // Setup
        com.example.atletikstaevne_backend.models.Deltager deltager = new com.example.atletikstaevne_backend.models.Deltager();
        deltager.setNavn("John Doe");
        deltager.setKoen("Male");
        deltager.setAlder(30);
        deltager.setKlub("ABC Club");
        deltagerRepository.save(deltager);

        com.example.atletikstaevne_backend.models.Disciplin disciplin = new com.example.atletikstaevne_backend.models.Disciplin();
        disciplin.setNavn("100m løb");
        disciplin.setResultatType(ResultatType.TID);
        disciplinRepository.save(disciplin);

        com.example.atletikstaevne_backend.models.Resultat resultat = new com.example.atletikstaevne_backend.models.Resultat();
        resultat.setResultatType(ResultatType.TID);
        resultat.setDato(LocalDate.now());
        resultat.setResultatvaerdi("10.00");
        resultat.setDeltager(deltager);
        resultat.setDisciplin(disciplin);
        resultatRepository.save(resultat);

        // Perform and verify
        mockMvc.perform(delete("/api/resultater/" + resultat.getResultatId()))
                .andExpect(status().isNoContent());
    }
}
