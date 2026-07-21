package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.NoteDTO;
import com.ProjetILEIC.ILIEC.dto.NoteRequestDTO;
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
import java.util.stream.Collectors;

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

    // Update list methods and add toDTO
    public List<NoteDTO> getNotesByStagiaire(Long stagiaireId) {
        return noteRepository.findByStagiaire_Id(stagiaireId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
    public NoteDTO toDTO(Note n) {
        return new NoteDTO(
                n.getId(),
                n.getStagiaire().getId(),
                n.getStagiaire().getUser().getFullName(),
                n.getControle().getId(),
                n.getControle().getTitle(),
                n.getRecordedBy().getId(),
                n.getRecordedBy().getFullName(),
                n.getGradeValue(),
                n.getRemarks()
        );
    }

    @Transactional(readOnly = true)
    public List<NoteDTO> getNotesByControle(Long controleId) {
        return noteRepository.findByControle_Id(controleId)
                .stream().map(this::toDTO).collect(Collectors.toList());
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

    public NoteDTO createNote(NoteRequestDTO dto) {
        if (dto.getGradeValue().compareTo(BigDecimal.ZERO) < 0
                || dto.getGradeValue().compareTo(BigDecimal.valueOf(20)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }
        if (noteRepository.findByStagiaire_IdAndControle_Id(
                dto.getStagiaireId(), dto.getControleId()).isPresent()) {
            throw new DuplicateResourceException(
                    "A grade already exists for stagiaire " + dto.getStagiaireId()
                            + " in controle " + dto.getControleId());
        }
        Stagiaire stagiaire = stagiaireRepository.findById(dto.getStagiaireId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Stagiaire not found: " + dto.getStagiaireId()));
        Controle controle = controleRepository.findById(dto.getControleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Controle not found: " + dto.getControleId()));
        User recordedBy = userRepository.findById(dto.getRecordedById())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found: " + dto.getRecordedById()));

        Note note = new Note();
        note.setStagiaire(stagiaire);
        note.setControle(controle);
        note.setRecordedBy(recordedBy);
        note.setGradeValue(dto.getGradeValue());
        note.setRemarks(dto.getRemarks());

        return toDTO(noteRepository.save(note));
    }

    // --- UPDATE ---

    public NoteDTO updateNote(Long id, NoteRequestDTO dto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + id));
        if (dto.getGradeValue().compareTo(BigDecimal.ZERO) < 0
                || dto.getGradeValue().compareTo(BigDecimal.valueOf(20)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }
        note.setGradeValue(dto.getGradeValue());
        note.setRemarks(dto.getRemarks());
        return toDTO(noteRepository.save(note));
    }
}
