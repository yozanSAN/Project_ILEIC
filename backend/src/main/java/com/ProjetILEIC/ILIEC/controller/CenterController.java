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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<CentreDTO>> getAllCentres() {
        return ResponseEntity.ok(centreService.getAllCentres());
    }

    //GET CENTRE BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<CentreDTO> getCentreById(@PathVariable Long id) {
        return ResponseEntity.ok(centreService.getCentreById(id));
    }

    //CREATE NEW CENTRE
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<CentreDTO> createCentre(@Valid @RequestBody CentreRequestDTO requestDTO) {
        CentreDTO created = centreService.createCentre(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //UPDATE INOFORMATIONS ABOUT A SPECIFIC CENTRE

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<CentreDTO> updateCentre(
            @PathVariable Long id,
            @Valid @RequestBody CentreRequestDTO requestDTO) {
        return ResponseEntity.ok(centreService.updateCentre(id, requestDTO));
    }

    //DELETE A SPECIFIC CENTRE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        centreService.deleteCentre(id);
        return ResponseEntity.noContent().build();
    }
}