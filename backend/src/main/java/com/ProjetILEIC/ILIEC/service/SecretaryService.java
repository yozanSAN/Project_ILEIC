package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.SecretaryDTO;
import com.ProjetILEIC.ILIEC.dto.SecretaryRequestDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.entity.Secretary;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.CentreRepository;
import com.ProjetILEIC.ILIEC.repository.SecretaryRepository;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;
    private final UserRepository userRepository;
    private final CentreRepository centreRepository;

    public SecretaryService(SecretaryRepository secretaryRepository,
                            UserRepository userRepository,
                            CentreRepository centreRepository) {
        this.secretaryRepository = secretaryRepository;
        this.userRepository = userRepository;
        this.centreRepository = centreRepository;
    }

    // --- READ ---

    @Transactional(readOnly = true)
    public List<SecretaryDTO> getAllSecretaries() {
        return secretaryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SecretaryDTO getSecretaryById(Long id) {
        return toDTO(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<SecretaryDTO> getByCentre(Long centreId) {
        return secretaryRepository.findByCentre_Id(centreId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --- CREATE ---

    public SecretaryDTO createSecretary(SecretaryRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();
        Long centreId = requestDTO.getCentreId();
        //  1 — check this user isn't already a secretary
        if (secretaryRepository.findByUser_Id(userId).isPresent()) {
            throw new DuplicateResourceException("User " + userId + " is already a secretary");
        }

        // 2 — check if the centre already has a secretary
        if (secretaryRepository.findByCentre_Id(centreId).size() > 0) {
            throw new DuplicateResourceException("Centre " + centreId + " already has a secretary assigned");
        }

        //  3 — fetch dependencies
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + centreId));

        // 4 — business rule: user must have SECRETAIRE role
        if (user.getRole() != User.Role.SECRETAIRE) {
            throw new IllegalArgumentException("User must have SECRETAIRE role to be a secretary");
        }

        //  5 — build and save
        Secretary newSecretary = new Secretary();
        newSecretary.setUser(user);
        newSecretary.setCentre(centre);

        return toDTO(secretaryRepository.save(newSecretary));
    }

    //---UPDATE
    public SecretaryDTO updateSecretary(Long id, SecretaryRequestDTO requestDTO) {
        Long centreId = requestDTO.getCentreId();
        Secretary existing = findOrThrow(id);

        // Only check for a conflict if the centre assignment is actually changing
        if (!existing.getCentre().getId().equals(centreId)) {
            if (secretaryRepository.findByCentre_Id(centreId).size() > 0) {
                throw new DuplicateResourceException("Centre " + centreId + " already has a secretary assigned");
            }
        }
        // Fetch the new centre assignment
        Centre newCentre = centreRepository.findById(requestDTO.getCentreId())
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + requestDTO.getCentreId()));

        // Note: Generally, you don't swap the underlying User account on a profile record,
        // but if you do need to update the centre assignment, we handle it here:
        existing.setCentre(newCentre);

        return toDTO(secretaryRepository.save(existing));
    }

    // --- DELETE ---
    public void deleteSecretary(Long id) {
        if (!secretaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Secretary not found with id: " + id);
        }
        secretaryRepository.deleteById(id);
    }

    // --- HELPERS ---
    private Secretary findOrThrow(Long id) {
        return secretaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Secretary not found with id: " + id));
    }

    // --- DTO CONVERSION ---
    public SecretaryDTO toDTO(Secretary s) {
        return new SecretaryDTO(
                s.getId(),
                s.getUser().getId(),
                s.getUser().getFullName(),
                s.getUser().getEmail(),
                s.getUser().getPhone(),
                s.getCentre().getId(),
                s.getCentre().getName()
        );
    }
}
