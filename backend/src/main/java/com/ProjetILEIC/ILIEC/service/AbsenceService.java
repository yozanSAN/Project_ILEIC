package com.ProjetILEIC.ILIEC.service;

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

import java.time.LocalDate;
import java.util.List;

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
    public List<Absence> getAbsencesByStagiaire(Long stagiaireId) {
        return absenceRepository.findByStagiaire_Id(stagiaireId);
    }

    @Transactional(readOnly = true)
    public List<Absence> getAbsencesByCours(Long coursId) {
        return absenceRepository.findByCours_Id(coursId);
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

    public Absence recordAbsence(Long stagiaireId, Long coursId, Long recordedById,
                                 LocalDate date, String status, String remarks) {

        // Step 1 — fetch dependencies
        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire not found: " + stagiaireId));

        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found: " + coursId));

        User recordedBy = userRepository.findById(recordedById)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + recordedById));

        // Step 2 — business rule: filiere of cours must match filiere of stagiaire
        if (!cours.getFiliere().getId().equals(stagiaire.getFiliere().getId())) {
            throw new IllegalArgumentException("Cours does not belong to this stagiaire's filiere");
        }

        // Step 3 — build and save
        Absence absence = new Absence();
        absence.setStagiaire(stagiaire);
        absence.setCours(cours);
        absence.setRecordedBy(recordedBy);
        absence.setDate(date);
        absence.setStatus(status);
        absence.setRemarks(remarks);

        return absenceRepository.save(absence);
    }

    // --- UPDATE (justify an absence) ---

    public Absence justifyAbsence(Long id, String remarks) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absence not found with id: " + id));

        absence.setStatus("JUSTIFIED");
        absence.setRemarks(remarks);
        return absenceRepository.save(absence);
    }
}
