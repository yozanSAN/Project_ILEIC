package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.StagiaireDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.entity.Filiere;
import com.ProjetILEIC.ILIEC.entity.Stagiaire;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CentreRepository;
import com.ProjetILEIC.ILIEC.repository.FiliereRepository;
import com.ProjetILEIC.ILIEC.repository.StagiaireRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StagiaireService {

    private final StagiaireRepository stagiaireRepository;
    private final UserRepository userRepository;
    private final CentreRepository centreRepository;
    private final FiliereRepository filiereRepository;

    public StagiaireService(StagiaireRepository stagiaireRepository,
                            UserRepository userRepository,
                            CentreRepository centreRepository,
                            FiliereRepository filiereRepository) {
        this.stagiaireRepository = stagiaireRepository;
        this.userRepository = userRepository;
        this.centreRepository = centreRepository;
        this.filiereRepository = filiereRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<StagiaireDTO> getAllStagiaires() {
        return stagiaireRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StagiaireDTO getStagiaireById(Long id) {
        return toDTO(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<StagiaireDTO> getByFiliere(Long filiereId) {
        return stagiaireRepository.findByFiliere_Id(filiereId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StagiaireDTO> getByCentre(Long centreId) {
        return stagiaireRepository.findByCentre_Id(centreId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //GET BY CENTER AND FILIERE
    @Transactional(readOnly = true)
    public List<StagiaireDTO> getByCentreAndFiliere(Long centreId, Long filiereId) {
        return stagiaireRepository.findByFiliere_IdAndCentre_Id(centreId , centreId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --- CREATE ---

    public StagiaireDTO createStagiaire(Stagiaire stagiaire, Long userId, Long centreId, Long filiereId) {

        // Step 1 — check registration number is unique
        if (stagiaireRepository.existsByRegistrationNumber(stagiaire.getRegistrationNumber())) {
            throw new DuplicateResourceException(
                    "Registration number already exists: " + stagiaire.getRegistrationNumber());
        }

        // Step 2 — fetch and validate dependencies
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + centreId));

        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + filiereId));

        // Step 3 — business rule: filiere must belong to the centre
        if (!filiere.getCentre().getId().equals(centreId)) {
            throw new IllegalArgumentException(
                    "Filiere " + filiereId + " does not belong to centre " + centreId);
        }

        // Step 4 — attach and save
        stagiaire.setUser(user);
        stagiaire.setCentre(centre);
        stagiaire.setFiliere(filiere);

        return toDTO(stagiaireRepository.save(stagiaire));
    }

    // --- UPDATE ---

    public StagiaireDTO updateStagiaire(Long id, Stagiaire updated) {
        Stagiaire existing = findOrThrow(id);

        existing.setBirthDate(updated.getBirthDate());
        existing.setCin(updated.getCin());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        existing.setStatus(updated.getStatus());

        return toDTO(stagiaireRepository.save(existing));
    }

    // --- DELETE ---

    public void deleteStagiaire(Long id) {
        if (!stagiaireRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stagiaire not found with id: " + id);
        }
        stagiaireRepository.deleteById(id);
    }

    // --- HELPERS ---

    private Stagiaire findOrThrow(Long id) {
        return stagiaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire not found with id: " + id));
    }

    // --- DTO CONVERSION ---

    public StagiaireDTO toDTO(Stagiaire s) {
        return new StagiaireDTO(
                s.getId(),
                s.getUser().getId(),
                s.getUser().getFullName(),
                s.getUser().getEmail(),
                s.getCentre().getId(),
                s.getCentre().getName(),
                s.getFiliere().getId(),
                s.getFiliere().getName(),
                s.getRegistrationNumber(),
                s.getBirthDate(),
                s.getCin(),
                s.getPhone(),
                s.getAddress(),
                s.getEnrollmentDate(),
                s.getStatus()
        );
    }


}
