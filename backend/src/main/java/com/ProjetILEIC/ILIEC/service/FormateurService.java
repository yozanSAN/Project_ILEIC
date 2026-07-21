package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.FormateurDTO;
import com.ProjetILEIC.ILIEC.dto.FormateurRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.entity.Formateur;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
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
                .map(cid -> {
                    Centre centre = centreRepository.findById(cid)
                            .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + cid));

                    // Security Guard: Prevent assignments to an inactive center
                    if (!centre.getIsActive()) {
                        throw new IllegalArgumentException("Cannot assign trainer to an inactive center with ID: " + cid);
                    }
                    return centre;
                })
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

        //Security Guard: Prevent fresh assignment onto an inactive location
        if (!centre.getIsActive()) {
            throw new IllegalArgumentException("Cannot map instructors to an inactive center location.");
        }

        boolean alreadyAssigned = formateur.getCentres().stream()
                .anyMatch(c -> c.getId().equals(centreId));
        if (alreadyAssigned) {
            throw new DuplicateResourceException("Formateur already assigned to centre: " + centreId);
        }

        formateur.getCentres().add(centre);
        return toDTO(formateurRepository.save(formateur));
    }

    // --- SOFT DELETE ---
    public void deactivateFormateur(Long id) {
        Formateur formateur = formateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found with id: " + id));

        // Safely flags the login entity deactivated instead of purging row history
        formateur.getUser().setIsActive(false);
        formateurRepository.save(formateur);
    }

    @Transactional
    public void removeFormateurFromCentre(Long formateurId, Long centreId) {
        Formateur formateur = formateurRepository.findById(formateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Formateur not found with id: " + formateurId));

        //find the target centre inside the formateur list of centres
        Centre centreToRemove = formateur.getCentres().stream()
                .filter(c -> c.getId().equals(centreId))
                        .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formateur is not assigned to this centre"));
        // Business Guard Check: Is this instructor the last remaining resource at that center?
        List<Formateur> activeTrainersAtCentre = formateurRepository.findByCentres_Id(centreId);
        if (activeTrainersAtCentre.size() <= 1) {
            throw new IllegalArgumentException("Cannot remove instructor: A training center must have at least one assigned instructor.");
        }
        formateur.getCentres().remove(centreToRemove);
        formateurRepository.save(formateur);
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
