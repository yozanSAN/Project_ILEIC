package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.ModulesFormateurDTO;
import com.ProjetILEIC.ILIEC.dto.ModulesFormateurRequestDTO;
import com.ProjetILEIC.ILIEC.service.ModulesFormateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module-formateur")
public class ModulesFormateurController {

    private final ModulesFormateurService service;

    public ModulesFormateurController(ModulesFormateurService service) {
        this.service = service;
    }

    // GET : retrieve all course modules assigned to a specific teacher
    @GetMapping("/formateur/{formateurId}")
    public ResponseEntity<List<ModulesFormateurDTO>> getByFormateur(@PathVariable Long formateurId) {
        return ResponseEntity.ok(service.getAssignmentsByFormateur(formateurId));
    }

    // GET : retrieve all teachers assigned to teach a specific course module
    @GetMapping("/cours/{coursId}")
    public ResponseEntity<List<ModulesFormateurDTO>> getByCours(@PathVariable Long coursId) {
        return ResponseEntity.ok(service.getAssignmentsByCours(coursId));
    }

    // POST : assign a course module to a teacher
    @PostMapping
    public ResponseEntity<ModulesFormateurDTO> assignModule(@Valid @RequestBody ModulesFormateurRequestDTO requestDTO) {
        ModulesFormateurDTO created = service.assignModule(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT : update a specific teacher module assignment parameters
    @PutMapping("/{id}")
    public ResponseEntity<ModulesFormateurDTO> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody ModulesFormateurRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateAssignment(id, requestDTO));
    }

    // DELETE : unassign a module from a teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAssignment(@PathVariable Long id) {
        service.removeAssignment(id);
        return ResponseEntity.noContent().build();
    }
}

/*
 * ============================================================================
 * ENDPOINT DESCRIPTIONS (HOW THIS CONTROLLER WORKS)
 * ============================================================================
 * * 1. GET /formateur/{id}
 * - Looks up a specific teacher and shows you a list of all subjects they are
 * assigned to teach (e.g., "Show me everything Mr. Karim teaches").
 *
 * * 2. GET /cours/{id}
 * - Looks up a specific subject and shows you all teachers assigned to it
 * (e.g., "Show me all teachers who can teach Java Spring Boot").
 *
 * * 3. POST /
 * - Creates a brand-new relationship link between a teacher and a subject.
 * It also records what semester (1, 2, 3, 4) and school year they are doing it.
 * *Rule:* Prevents assigning the same teacher to the same subject twice.
 *
 * * 4. PUT /{id}
 * - Modifies an existing link. Use this if a teacher changes their assigned
 * subject, or if the administration shifts the class to a different semester.
 *
 * * 5. DELETE /{id}
 * - Destroys the link. Use this if a teacher stops teaching a certain module
 * entirely, unlinking them in the database without deleting the actual teacher
 * or the actual subject.
 */
