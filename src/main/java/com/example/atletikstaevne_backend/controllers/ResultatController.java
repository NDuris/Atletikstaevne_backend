package com.example.atletikstaevne_backend.controllers;

import com.example.atletikstaevne_backend.DTO.ResultatDTO;
import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.models.Disciplin;
import com.example.atletikstaevne_backend.models.Resultat;
import com.example.atletikstaevne_backend.services.DeltagerService;
import com.example.atletikstaevne_backend.services.DisciplinService;
import com.example.atletikstaevne_backend.services.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resultater")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @Autowired
    private DeltagerService deltagerService;

    @Autowired
    private DisciplinService disciplinService;

    @GetMapping
    public ResponseEntity<List<ResultatDTO>> getAllResultater() {
        List<Resultat> resultater = resultatService.findAll();

        // Konverter alle resultater til DTO-format
        List<ResultatDTO> resultatDTOs = resultater.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultatDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultatDTO> getResultatById(@PathVariable Long id) {
        Optional<Resultat> resultatOptional = resultatService.findById(id);
        if (resultatOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ResultatDTO resultatDTO = convertToDTO(resultatOptional.get());
        return ResponseEntity.ok(resultatDTO);
    }

    // PUT endpoint til at opdatere et resultat
    @PutMapping("/{id}")
    public ResponseEntity<ResultatDTO> updateResultat(@PathVariable Long id, @RequestBody ResultatDTO updatedResultatDTO) {
        Optional<Resultat> resultatOptional = resultatService.findById(id);
        if (resultatOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Resultat existingResultat = resultatOptional.get();

        // Opdater existingResultat med de nye værdier fra updatedResultatDTO
        existingResultat.setResultatType(updatedResultatDTO.getResultatType());
        existingResultat.setResultatvaerdi(updatedResultatDTO.getResultatvaerdi());
        existingResultat.setDato(updatedResultatDTO.getDato());

        // Gem den opdaterede existingResultat i databasen
        Resultat updatedResultat = resultatService.save(existingResultat);

        // Konverter resultatet til DTO-format og returner det
        ResultatDTO resultatDTO = convertToDTO(updatedResultat);
        return ResponseEntity.ok(resultatDTO);
    }


    @PostMapping
    public ResponseEntity<ResultatDTO> createResultat(@RequestBody ResultatDTO resultatDTO) {
        Optional<Deltager> deltagerOptional = deltagerService.findById(resultatDTO.getDeltagerId());
        Optional<Disciplin> disciplinOptional = disciplinService.findById(resultatDTO.getDisciplinId());

        if (deltagerOptional.isEmpty() || disciplinOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Deltager deltager = deltagerOptional.get();
        Disciplin disciplin = disciplinOptional.get();

        Resultat resultat = new Resultat();
        resultat.setDeltager(deltager);
        resultat.setDisciplin(disciplin);
        resultat.setResultatType(resultatDTO.getResultatType());
        resultat.setResultatvaerdi(resultatDTO.getResultatvaerdi());
        resultat.setDato(resultatDTO.getDato());

        Resultat savedResultat = resultatService.save(resultat);

        ResultatDTO savedResultatDTO = convertToDTO(savedResultat);

        return ResponseEntity.ok(savedResultatDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        resultatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert Resultat entity to ResultatDTO
    private ResultatDTO convertToDTO(Resultat resultat) {
        ResultatDTO resultatDTO = new ResultatDTO();
        resultatDTO.setId(resultat.getResultatId());
        resultatDTO.setDeltagerId(resultat.getDeltager().getDeltagerId());
        resultatDTO.setDisciplinId(resultat.getDisciplin().getDisciplinId());
        resultatDTO.setResultatType(resultat.getResultatType());
        resultatDTO.setResultatvaerdi(resultat.getResultatvaerdi());
        resultatDTO.setDato(resultat.getDato());
        return resultatDTO;
    }
}
