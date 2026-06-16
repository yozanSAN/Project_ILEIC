package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.StagiaireDTO;
import com.ProjetILEIC.ILIEC.dto.StagiaireRequestDTO;
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
        return stagiaireRepository.findByFiliere_IdAndCentre_Id(centreId , filiereId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Create
    public StagiaireRequestDTO createStagiaire(StagiaireRequestDTO dto) {
        if (stagiaireRepository.existsByRegistrationNumber(dto.getRegistrationNumber())) {
            throw new DuplicateResourceException("Registration number already exists: " + dto.getRegistrationNumber());
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        Centre centre = centreRepository.findById(dto.getCentreId())
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + dto.getCentreId()));
        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new ResourceNotFoundException("Filiere not found with id: " + dto.getFiliereId()));

        Stagiaire stagiaire = new Stagiaire();
        // Map fields
        stagiaire.setRegistrationNumber(dto.getRegistrationNumber());
        stagiaire.setBirthDate(dto.getBirthDate());
        stagiaire.setCin(dto.getCin());
        stagiaire.setPhone(dto.getPhone());
        stagiaire.setAddress(dto.getAddress());
        stagiaire.setEnrollmentDate(dto.getEnrollmentDate());
        stagiaire.setStatus(dto.getStatus());
        stagiaire.setUser(user);
        stagiaire.setCentre(centre);
        stagiaire.setFiliere(filiere);

        Stagiaire savedStagiaire = stagiaireRepository.save(stagiaire);

        return toRequestDTO(savedStagiaire);
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
    // --- NEW DTO CONVERSION FOR POST RESPONSES ---
    private StagiaireRequestDTO toRequestDTO(Stagiaire s) {
        return new StagiaireRequestDTO(
                s.getRegistrationNumber(),
                s.getBirthDate(),
                s.getCin(),
                s.getPhone(),
                s.getAddress(),
                s.getEnrollmentDate(),
                s.getStatus(),
                s.getUser().getId(),
                s.getCentre().getId(),
                s.getFiliere().getId()
        );
    }
}
