package com.example.atletikstaevne_backend;

import com.example.atletikstaevne_backend.DTO.DeltagerDTO;
import com.example.atletikstaevne_backend.repositorys.DeltagerRepository;
import com.example.atletikstaevne_backend.repositorys.DisciplinRepository;
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
public class DeltagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON

    @BeforeEach
    public void setup() {
        deltagerRepository.deleteAll();
        disciplinRepository.deleteAll();
    }

    @Test
    public void testGetAllDeltagere() throws Exception {
        // Setup
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("John Doe");
        deltagerDTO.setKoen("Male");
        deltagerDTO.setAlder(30);
        deltagerDTO.setKlub("ABC Club");
        createDeltager(deltagerDTO);

        // Perform and verify
        mockMvc.perform(get("/api/deltagere"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].navn", is("John Doe")));
    }

    @Test
    public void testCreateDeltager() throws Exception {
        // Setup
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("John Doe");
        deltagerDTO.setKoen("Male");
        deltagerDTO.setAlder(30);
        deltagerDTO.setKlub("ABC Club");

        // Perform and verify
        mockMvc.perform(post("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltagerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.navn", is("John Doe")));
    }

    @Test
    public void testUpdateDeltager() throws Exception {
        // Setup
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("John Doe");
        deltagerDTO.setKoen("Male");
        deltagerDTO.setAlder(30);
        deltagerDTO.setKlub("ABC Club");
        Long deltagerId = createDeltager(deltagerDTO);

        // Modify DTO
        deltagerDTO.setNavn("Jane Doe");

        // Perform and verify
        mockMvc.perform(put("/api/deltagere/" + deltagerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltagerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.navn", is("Jane Doe")));
    }

    @Test
    public void testDeleteDeltager() throws Exception {
        // Setup
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("John Doe");
        deltagerDTO.setKoen("Male");
        deltagerDTO.setAlder(30);
        deltagerDTO.setKlub("ABC Club");
        Long deltagerId = createDeltager(deltagerDTO);

        // Perform and verify
        mockMvc.perform(delete("/api/deltagere/" + deltagerId))
                .andExpect(status().isNoContent());
    }

    private Long createDeltager(DeltagerDTO deltagerDTO) throws Exception {
        String response = mockMvc.perform(post("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltagerDTO)))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, DeltagerDTO.class).getId();
    }
}
