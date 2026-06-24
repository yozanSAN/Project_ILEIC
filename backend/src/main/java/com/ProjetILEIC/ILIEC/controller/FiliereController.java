package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.FiliereDTO;
import com.ProjetILEIC.ILIEC.service.FiliereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {
    private final FiliereService filiereService;

    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

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

}
