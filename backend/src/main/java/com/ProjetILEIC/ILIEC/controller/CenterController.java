package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.CentreDTO;
import com.ProjetILEIC.ILIEC.dto.CentreRequestDTO;
import com.ProjetILEIC.ILIEC.service.CentreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centres")
public class CenterController {

    private final CentreService centreService;

    public CenterController(CentreService centreService) {
        this.centreService = centreService;
    }

    //GET ALL CENTRES
    @GetMapping
    public ResponseEntity<List<CentreDTO>> getAllCentres() {
        return ResponseEntity.ok(centreService.getAllCentres());
    }

    //GET CENTRE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CentreDTO> getCentreById(@PathVariable Long id) {
        return ResponseEntity.ok(centreService.getCentreById(id));
    }

    //CREATE NEW CENTRE
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CentreDTO> createCentre(@RequestBody CentreRequestDTO requestDTO) {
        CentreDTO created = centreService.createCentre(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //UPDATE INOFORMATIONS ABOUT A SPECIFIC CENTRE
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CentreDTO> updateCentre(
            @PathVariable Long id,
            @Valid @RequestBody CentreRequestDTO requestDTO) {
        return ResponseEntity.ok(centreService.updateCentre(id, requestDTO));
    }

    //DELETE A SPECIFIC CENTRE
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        centreService.deleteCentre(id);
        return ResponseEntity.noContent().build();
    }
}