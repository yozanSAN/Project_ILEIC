package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.CentreDTO;
import com.ProjetILEIC.ILIEC.dto.CentreRequestDTO;
import com.ProjetILEIC.ILIEC.service.CentreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centres")
public class CenterController {

    private final CentreService centreService;

    public CenterController(CentreService centreService) {
        this.centreService = centreService;
    }

    @GetMapping
    public ResponseEntity<List<CentreDTO>> getAllCentres() {
        return ResponseEntity.ok(centreService.getAllCentres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentreDTO> getCentreById(@PathVariable Long id) {
        return ResponseEntity.ok(centreService.getCentreById(id));
    }

    @PostMapping
    public ResponseEntity<CentreDTO> createCentre(@RequestBody CentreRequestDTO requestDTO) {
        CentreDTO created = centreService.createCentre(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreDTO> updateCentre(
            @PathVariable Long id,
            @Valid @RequestBody CentreRequestDTO requestDTO) {
        return ResponseEntity.ok(centreService.updateCentre(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        centreService.deleteCentre(id);
        return ResponseEntity.noContent().build();
    }
}