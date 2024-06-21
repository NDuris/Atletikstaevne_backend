package com.example.atletikstaevne_backend.controllers;

import com.example.atletikstaevne_backend.DTO.DisciplinDTO;
import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.models.Disciplin;
import com.example.atletikstaevne_backend.models.Resultat;
import com.example.atletikstaevne_backend.models.ResultatType;
import com.example.atletikstaevne_backend.services.DisciplinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/discipliner")
public class DisciplinController {

    @Autowired
    private DisciplinService disciplinService;

    @GetMapping
    public List<DisciplinDTO> getAllDiscipliner() {
        List<Disciplin> disciplinList = disciplinService.findAll();
        return disciplinList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinDTO> getDisciplinById(@PathVariable Long id) {
        Optional<Disciplin> disciplin = disciplinService.findById(id);
        if (disciplin.isPresent()) {
            return ResponseEntity.ok(convertToDto(disciplin.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/deltagere")
    public List<Deltager> getDeltagereByDisciplinId(@PathVariable Long id) {
        return disciplinService.findDeltagereByDisciplinId(id);
    }

    @GetMapping("/{id}/resultater")
    public List<Resultat> getResultaterByDisciplinId(@PathVariable Long id) {
        return disciplinService.findResultaterByDisciplinId(id);
    }

    @PostMapping
    public DisciplinDTO createDisciplin(@RequestBody DisciplinDTO disciplinDTO) {
        Disciplin disciplin = new Disciplin();
        disciplin.setNavn(disciplinDTO.getNavn());
        disciplin.setResultatType(disciplinDTO.getResultatType());
        Disciplin savedDisciplin = disciplinService.save(disciplin);
        return convertToDto(savedDisciplin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinDTO> updateDisciplin(@PathVariable Long id, @RequestBody DisciplinDTO disciplinDTO) {
        Optional<Disciplin> optionalDisciplin = disciplinService.findById(id);
        if (optionalDisciplin.isPresent()) {
            Disciplin disciplinToUpdate = optionalDisciplin.get();
            disciplinToUpdate.setNavn(disciplinDTO.getNavn());
            disciplinToUpdate.setResultatType(disciplinDTO.getResultatType());
            Disciplin updatedDisciplin = disciplinService.save(disciplinToUpdate);
            return ResponseEntity.ok(convertToDto(updatedDisciplin));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplin(@PathVariable Long id) {
        disciplinService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert Disciplin entity to DisciplinDTO
    private DisciplinDTO convertToDto(Disciplin disciplin) {
        DisciplinDTO disciplinDTO = new DisciplinDTO();
        disciplinDTO.setId(disciplin.getDisciplinId());
        disciplinDTO.setNavn(disciplin.getNavn());
        disciplinDTO.setResultatType(disciplin.getResultatType());
        return disciplinDTO;
    }
}
