package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.FiliereDTO;
import com.ProjetILEIC.ILIEC.dto.FiliereRequestDTO;
import com.ProjetILEIC.ILIEC.service.FiliereService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {
    private final FiliereService filiereService;

    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    //-------------------GET-----------------------
    // GET ALL ACADIMEC FILIERES
    @GetMapping
    public ResponseEntity<List<FiliereDTO>> getAllFilieres(){
        return ResponseEntity.ok(filiereService.getAllFilieres());
    }

    //RETRIEVE DETAILED INFORMATIONS FOR A SINGLE SPECIFIC FILIERE
    @GetMapping("/{id}")
    public ResponseEntity<FiliereDTO> getFilieById(@PathVariable Long id){
        return ResponseEntity.ok(filiereService.getFiliereById(id));
    }

    //RETRIEVE ALL FILIERES THAT BELONGS TO A SPECIFIC CRENTRE
    @GetMapping("/centre/{centreId}")
    public ResponseEntity<List<FiliereDTO>> getFiliereByCentre(@PathVariable Long centreId){
            return ResponseEntity.ok(filiereService.getFiliereByCentre(centreId));
    }

    //-------------------CREATE-----------------------
    // Creates a new filiere record.
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<FiliereDTO>createFiliere(@RequestBody FiliereRequestDTO requestDTO){
        FiliereDTO created = filiereService.createFiliere(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //-------------------UPDATE-----------------------
    // Completely updates an existing filiere record by its ID.
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<FiliereDTO> updateFiliere(
            @PathVariable Long id,
            @RequestBody FiliereRequestDTO requestDTO) {
        return ResponseEntity.ok(filiereService.updateFiliere(id, requestDTO));
    }

    //-------------------DELETE-----------------------
    //Permanently deletes a filiere record from the database.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<Void> deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.noContent().build();
    }



}
