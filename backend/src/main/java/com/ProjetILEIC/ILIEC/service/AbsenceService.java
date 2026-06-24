package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.AbsenceDTO;
import com.ProjetILEIC.ILIEC.dto.AbsenceRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Absence;
import com.ProjetILEIC.ILIEC.entity.Cours;
import com.ProjetILEIC.ILIEC.entity.Stagiaire;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.AbsenceRepository;
import com.ProjetILEIC.ILIEC.repository.CoursRepository;
import com.ProjetILEIC.ILIEC.repository.StagiaireRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final StagiaireRepository stagiaireRepository;
    private final CoursRepository coursRepository;
    private final UserRepository userRepository;

    // OFPPT rule: stagiaire is at risk of exclusion after this many unjustified absences
    private static final long ABSENCE_LIMIT = 10;

    public AbsenceService(AbsenceRepository absenceRepository,
                          StagiaireRepository stagiaireRepository,
                          CoursRepository coursRepository,
                          UserRepository userRepository) {
        this.absenceRepository = absenceRepository;
        this.stagiaireRepository = stagiaireRepository;
        this.coursRepository = coursRepository;
        this.userRepository = userRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<AbsenceDTO> getAbsencesByStagiaire(Long stagiaireId) {
        return absenceRepository.findByStagiaire_Id(stagiaireId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AbsenceDTO> getAbsencesByCours(Long coursId) {
        return absenceRepository.findByCours_Id(coursId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long countUnjustifiedAbsences(Long stagiaireId) {
        return absenceRepository.countByStagiaire_IdAndStatus(stagiaireId, "UNJUSTIFIED");
    }

    @Transactional(readOnly = true)
    public boolean isAtRisk(Long stagiaireId) {
        return countUnjustifiedAbsences(stagiaireId) >= ABSENCE_LIMIT;
    }

    // --- CREATE ---
    public AbsenceDTO recordAbsence(AbsenceRequestDTO requestDTO) {

        // Step 1 — fetch dependencies using ids from request DTO
        Stagiaire stagiaire = stagiaireRepository.findById(requestDTO.getStagiaireId())
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire not found: " + requestDTO.getStagiaireId()));

        Cours cours = coursRepository.findById(requestDTO.getCoursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found: " + requestDTO.getCoursId()));

        User recordedBy = userRepository.findById(requestDTO.getRecordedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + requestDTO.getRecordedById()));

        // Step 2 — business rule: filiere of cours must match filiere of stagiaire
        if (!cours.getFiliere().getId().equals(stagiaire.getFiliere().getId())) {
            throw new IllegalArgumentException("Cours does not belong to this stagiaire's filiere");
        }

        // Step 3 — build and save
        Absence absence = new Absence();
        absence.setStagiaire(stagiaire);
        absence.setCours(cours);
        absence.setRecordedBy(recordedBy);
        absence.setDate(requestDTO.getDate());
        absence.setStatus(requestDTO.getStatus());
        absence.setRemarks(requestDTO.getRemarks());

        return toDTO(absenceRepository.save(absence));
    }

    // --- UPDATE (justify an absence) ---

    public AbsenceDTO justifyAbsence(Long id, String remarks) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absence not found with id: " + id));

        absence.setStatus("JUSTIFIED");
        absence.setRemarks(remarks);
        return toDTO(absenceRepository.save(absence));
    }

    // --- DTO CONVERSION ---

    public AbsenceDTO toDTO(Absence a) {
        return new AbsenceDTO(
                a.getId(),
                a.getStagiaire().getId(),
                a.getStagiaire().getUser().getFullName(),
                a.getCours().getId(),
                a.getCours().getName(),
                a.getRecordedBy().getId(),
                a.getRecordedBy().getFullName(),
                a.getDate(),
                a.getStatus(),
                a.getRemarks()
        );
    }
}