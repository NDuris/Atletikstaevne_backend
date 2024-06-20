package com.example.atletikstaevne_backend;

import com.example.atletikstaevne_backend.DTO.DisciplinDTO;
import com.example.atletikstaevne_backend.repositorys.DisciplinRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DisciplinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        disciplinRepository.deleteAll();
    }

    @Test
    public void testGetAllDiscipliner() throws Exception {
        // Setup
        DisciplinDTO disciplinDTO = new DisciplinDTO();
        disciplinDTO.setNavn("100m løb");
        disciplinDTO.setResultatType(ResultatType.TID);
        createDisciplin(disciplinDTO);

        // Perform and verify
        mockMvc.perform(get("/api/discipliner"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].navn", is("100m løb")));
    }

    @Test
    public void testCreateDisciplin() throws Exception {
        // Setup
        DisciplinDTO disciplinDTO = new DisciplinDTO();
        disciplinDTO.setNavn("100m løb");
        disciplinDTO.setResultatType(ResultatType.TID);

        // Perform and verify
        mockMvc.perform(post("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplinDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.navn", is("100m løb")));
    }

    @Test
    public void testUpdateDisciplin() throws Exception {
        // Setup
        DisciplinDTO disciplinDTO = new DisciplinDTO();
        disciplinDTO.setNavn("100m løb");
        disciplinDTO.setResultatType(ResultatType.TID);
        Long disciplinId = createDisciplin(disciplinDTO);

        // Modify DTO
        disciplinDTO.setNavn("200m løb");

        // Perform and verify
        mockMvc.perform(put("/api/discipliner/" + disciplinId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplinDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.navn", is("200m løb")));
    }

    @Test
    public void testDeleteDisciplin() throws Exception {
        // Setup
        DisciplinDTO disciplinDTO = new DisciplinDTO();
        disciplinDTO.setNavn("100m løb");
        disciplinDTO.setResultatType(ResultatType.TID);
        Long disciplinId = createDisciplin(disciplinDTO);

        // Perform and verify
        mockMvc.perform(delete("/api/discipliner/" + disciplinId))
                .andExpect(status().isNoContent());
    }

    private Long createDisciplin(DisciplinDTO disciplinDTO) throws Exception {
        String response = mockMvc.perform(post("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplinDTO)))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, DisciplinDTO.class).getId();
    }
}
