package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.FormateurDTO;
import com.ProjetILEIC.ILIEC.dto.FormateurRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.entity.Formateur;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CentreRepository;
import com.ProjetILEIC.ILIEC.repository.FormateurRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FormateurService {

    private final FormateurRepository formateurRepository;
    private final UserRepository userRepository;
    private final CentreRepository centreRepository;

    public FormateurService(FormateurRepository formateurRepository,
                            UserRepository userRepository,
                            CentreRepository centreRepository) {
        this.formateurRepository = formateurRepository;
        this.userRepository = userRepository;
        this.centreRepository = centreRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<FormateurDTO> getAllFormateurs() {
        return formateurRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FormateurDTO getFormateurById(Long id) {
        return toDTO(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<FormateurDTO> getByCentre(Long centreId) {
        return formateurRepository.findByCentres_Id(centreId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --- CREATE ---

    public FormateurDTO createFormateur(Formateur formateur, Long userId, List<Long> centreIds) {

        // Step 1 — check this user isn't already a formateur
        if (formateurRepository.findByUser_Id(userId).isPresent()) {
            throw new DuplicateResourceException("User " + userId + " is already a formateur");
        }

        // Step 2 — fetch dependencies
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Centre> centres = centreIds.stream()
                .map(cid -> centreRepository.findById(cid)
                        .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + cid)))
                .collect(Collectors.toList());

        // Step 3 — business rule: user must have FORMATION role
        if (user.getRole() != User.Role.FORMATEUR) {
            throw new IllegalArgumentException("User must have FORMATEUR role to be a formateur");
        }

        // Step 4 — attach and save
        formateur.setUser(user);
        formateur.setCentres(centres);

        return toDTO(formateurRepository.save(formateur));
    }
    // DTO-based create: calls existing createFormateur(Formateur, Long, List<Long>) for all validation
    public FormateurDTO createFormateur(FormateurRequestDTO dto) {
        Formateur formateur = new Formateur();
        formateur.setSpeciality(dto.getSpeciality());
        formateur.setHireDate(dto.getHireDate());
        return createFormateur(formateur, dto.getUserId(), dto.getCentreIds());
    }

    // --- UPDATE ---
    public FormateurDTO updateFormateur(Long id, Formateur updated) {
        Formateur existing = findOrThrow(id);

        existing.setSpeciality(updated.getSpeciality());
        existing.setHireDate(updated.getHireDate());

        return toDTO(formateurRepository.save(existing));
    }
    // DTO-based update: calls existing updateFormateur(Long, Formateur)
    public FormateurDTO updateFormateur(Long id, FormateurRequestDTO dto) {
        Formateur formateur = new Formateur();
        formateur.setSpeciality(dto.getSpeciality());
        formateur.setHireDate(dto.getHireDate());
        return updateFormateur(id, formateur);
    }

    // Assign an additional centre to a formateur
    public FormateurDTO assignCentre(Long formateurId, Long centreId) {
        Formateur formateur = findOrThrow(formateurId);

        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + centreId));

        // Business rule: don't add the same centre twice
        boolean alreadyAssigned = formateur.getCentres().stream()
                .anyMatch(c -> c.getId().equals(centreId));
        if (alreadyAssigned) {
            throw new DuplicateResourceException("Formateur already assigned to centre: " + centreId);
        }

        formateur.getCentres().add(centre);
        return toDTO(formateurRepository.save(formateur));
    }

    // --- DELETE ---

    public void deleteFormateur(Long id) {
        if (!formateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Formateur not found with id: " + id);
        }
        formateurRepository.deleteById(id);
    }

    // --- HELPERS ---

    private Formateur findOrThrow(Long id) {
        return formateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found with id: " + id));
    }

    // --- DTO CONVERSION ---

    public FormateurDTO toDTO(Formateur f) {
        List<String> centreNames = f.getCentres().stream()
                .map(Centre::getName)
                .collect(Collectors.toList());

        return new FormateurDTO(
                f.getId(),
                f.getUser().getId(),
                f.getUser().getFullName(),
                f.getUser().getEmail(),
                f.getUser().getPhone(),
                f.getSpeciality(),
                f.getHireDate(),
                centreNames
        );
    }
}
