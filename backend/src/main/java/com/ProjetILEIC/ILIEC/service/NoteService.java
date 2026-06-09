package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.entity.Controle;
import com.ProjetILEIC.ILIEC.entity.Note;
import com.ProjetILEIC.ILIEC.entity.Stagiaire;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.ControleRepository;
import com.ProjetILEIC.ILIEC.repository.NoteRepository;
import com.ProjetILEIC.ILIEC.repository.StagiaireRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final StagiaireRepository stagiaireRepository;
    private final ControleRepository controleRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository,
                       StagiaireRepository stagiaireRepository,
                       ControleRepository controleRepository,
                       UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.stagiaireRepository = stagiaireRepository;
        this.controleRepository = controleRepository;
        this.userRepository = userRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<Note> getNotesByStagiaire(Long stagiaireId) {
        return noteRepository.findByStagiaire_Id(stagiaireId);
    }

    @Transactional(readOnly = true)
    public List<Note> getNotesByControle(Long controleId) {
        return noteRepository.findByControle_Id(controleId);
    }

    // Calculate the average grade for a stagiaire across all controles in a cours
    @Transactional(readOnly = true)
    public BigDecimal getAverageForStagiaireInCours(Long stagiaireId, Long coursId) {
        List<Note> notes = noteRepository.findByStagiaire_IdAndControle_Cours_Id(stagiaireId, coursId);

        if (notes.isEmpty()) return BigDecimal.ZERO;

        BigDecimal total = notes.stream()
                .map(Note::getGradeValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.divide(BigDecimal.valueOf(notes.size()), 2, RoundingMode.HALF_UP);
    }

    // --- CREATE ---

    public Note recordNote(Long stagiaireId, Long controleId, Long recordedById, BigDecimal gradeValue, String remarks) {

        // Step 1 — business rule: grade must be between 0 and 20
        if (gradeValue.compareTo(BigDecimal.ZERO) < 0 || gradeValue.compareTo(BigDecimal.valueOf(20)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }

        // Step 2 — check no duplicate grade for this stagiaire + controle
        if (noteRepository.findByStagiaire_IdAndControle_Id(stagiaireId, controleId).isPresent()) {
            throw new DuplicateResourceException(
                    "A grade already exists for stagiaire " + stagiaireId + " in controle " + controleId);
        }

        // Step 3 — fetch dependencies
        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire not found: " + stagiaireId));

        Controle controle = controleRepository.findById(controleId)
                .orElseThrow(() -> new ResourceNotFoundException("Controle not found: " + controleId));

        User recordedBy = userRepository.findById(recordedById)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + recordedById));

        // Step 4 — build and save
        Note note = new Note();
        note.setStagiaire(stagiaire);
        note.setControle(controle);
        note.setRecordedBy(recordedBy);
        note.setGradeValue(gradeValue);
        note.setRemarks(remarks);

        return noteRepository.save(note);
    }

    // --- UPDATE ---

    public Note updateNote(Long id, BigDecimal newGrade, String remarks) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + id));

        if (newGrade.compareTo(BigDecimal.ZERO) < 0 || newGrade.compareTo(BigDecimal.valueOf(20)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }

        note.setGradeValue(newGrade);
        note.setRemarks(remarks);
        return noteRepository.save(note);
    }
}
