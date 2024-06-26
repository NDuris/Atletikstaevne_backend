package com.example.atletikstaevne_backend;

import com.example.atletikstaevne_backend.DTO.DeltagerDTO;
import com.example.atletikstaevne_backend.DTO.DisciplinDTO;
import com.example.atletikstaevne_backend.DTO.ResultatDTO;
import com.example.atletikstaevne_backend.repositorys.DeltagerRepository;
import com.example.atletikstaevne_backend.repositorys.DisciplinRepository;
import com.example.atletikstaevne_backend.repositorys.ResultatRepository;
import com.example.atletikstaevne_backend.models.ResultatType;
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
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        resultatRepository.deleteAll();
        deltagerRepository.deleteAll();
        disciplinRepository.deleteAll();
    }

    @Test
    public void testGetAllResultater() throws Exception {
        // Setup
        Long deltagerId = createDeltager();
        Long disciplinId = createDisciplin();
        createResultat(deltagerId, disciplinId);

        // Perform and verify
        mockMvc.perform(get("/api/resultater"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].resultatvaerdi", is("10.5")));
    }

    @Test
    public void testCreateResultat() throws Exception {
        // Setup
        Long deltagerId = createDeltager();
        Long disciplinId = createDisciplin();
        ResultatDTO resultatDTO = new ResultatDTO();
        resultatDTO.setDeltagerId(deltagerId);
        resultatDTO.setDisciplinId(disciplinId);
        resultatDTO.setDato(LocalDate.now());
        resultatDTO.setResultatvaerdi("10.5");

        // Perform and verify
        mockMvc.perform(post("/api/resultater")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultatDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultatvaerdi", is("10.5")));
    }

    @Test
    public void testUpdateResultat() throws Exception {
        // Setup
        Long deltagerId = createDeltager();
        Long disciplinId = createDisciplin();
        Long resultatId = createResultat(deltagerId, disciplinId);

        // Modify DTO
        ResultatDTO resultatDTO = new ResultatDTO();
        resultatDTO.setDeltagerId(deltagerId);
        resultatDTO.setDisciplinId(disciplinId);
        resultatDTO.setDato(LocalDate.now());
        resultatDTO.setResultatvaerdi("12.3");

        // Perform and verify
        mockMvc.perform(put("/api/resultater/" + resultatId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultatDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultatvaerdi", is("12.3")));
    }

    @Test
    public void testDeleteResultat() throws Exception {
        // Setup
        Long deltagerId = createDeltager();
        Long disciplinId = createDisciplin();
        Long resultatId = createResultat(deltagerId, disciplinId);

        // Perform and verify
        mockMvc.perform(delete("/api/resultater/" + resultatId))
                .andExpect(status().isNoContent());
    }

    private Long createDeltager() throws Exception {
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("John Doe");
        deltagerDTO.setKoen("Male");
        deltagerDTO.setAlder(30);
        deltagerDTO.setKlub("ABC Club");
        String response = mockMvc.perform(post("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltagerDTO)))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, DeltagerDTO.class).getId();
    }

    private Long createDisciplin() throws Exception {
        DisciplinDTO disciplinDTO = new DisciplinDTO();
        disciplinDTO.setNavn("100m løb");
        disciplinDTO.setResultatType(ResultatType.TID);
        String response = mockMvc.perform(post("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplinDTO)))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, DisciplinDTO.class).getId();
    }

    private Long createResultat(Long deltagerId, Long disciplinId) throws Exception {
        ResultatDTO resultatDTO = new ResultatDTO();
        resultatDTO.setDeltagerId(deltagerId);
        resultatDTO.setDisciplinId(disciplinId);
        resultatDTO.setDato(LocalDate.now());
        resultatDTO.setResultatvaerdi("10.5");
        String response = mockMvc.perform(post("/api/resultater")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultatDTO)))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, ResultatDTO.class).getId();
    }
}