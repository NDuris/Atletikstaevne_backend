package com.example.atletikstaevne_backend.controllers;

import com.example.atletikstaevne_backend.DTO.DeltagerDTO;
import com.example.atletikstaevne_backend.models.Deltager;
import com.example.atletikstaevne_backend.services.DeltagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deltagere")
public class DeltagerController {
    @Autowired
    private DeltagerService deltagerService;

    @GetMapping
    public List<DeltagerDTO> getAllDeltagere() {
        List<Deltager> deltagerList = deltagerService.findAll();
        return deltagerList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeltagerDTO> getDeltagerById(@PathVariable Long id) {
        Optional<Deltager> deltager = deltagerService.findById(id);
        if (deltager.isPresent()) {
            return ResponseEntity.ok(convertToDto(deltager.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public DeltagerDTO createDeltager(@RequestBody DeltagerDTO deltagerDTO) {
        Deltager deltager = new Deltager();
        deltager.setNavn(deltagerDTO.getNavn());
        deltager.setKoen(deltagerDTO.getKoen());
        deltager.setAlder(deltagerDTO.getAlder());
        deltager.setKlub(deltagerDTO.getKlub());
        Deltager savedDeltager = deltagerService.save(deltager);
        return convertToDto(savedDeltager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeltagerDTO> updateDeltager(@PathVariable Long id, @RequestBody DeltagerDTO deltagerDTO) {
        Optional<Deltager> optionalDeltager = deltagerService.findById(id);
        if (optionalDeltager.isPresent()) {
            Deltager deltagerToUpdate = optionalDeltager.get();
            deltagerToUpdate.setNavn(deltagerDTO.getNavn());
            deltagerToUpdate.setKoen(deltagerDTO.getKoen());
            deltagerToUpdate.setAlder(deltagerDTO.getAlder());
            deltagerToUpdate.setKlub(deltagerDTO.getKlub());
            Deltager updatedDeltager = deltagerService.save(deltagerToUpdate);
            return ResponseEntity.ok(convertToDto(updatedDeltager));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeltager(@PathVariable Long id) {
        deltagerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<DeltagerDTO> searchDeltagere(@RequestParam String navn) {
        List<Deltager> deltagerList = deltagerService.findByNavn(navn);
        return deltagerList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Deltager entity to DeltagerDTO
    private DeltagerDTO convertToDto(Deltager deltager) {
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setId(deltager.getDeltagerId());
        deltagerDTO.setNavn(deltager.getNavn());
        deltagerDTO.setKoen(deltager.getKoen());
        deltagerDTO.setAlder(deltager.getAlder());
        deltagerDTO.setKlub(deltager.getKlub());
        return deltagerDTO;
    }
}
