package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.SecretaryDTO;
import com.ProjetILEIC.ILIEC.entity.Centre;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.entity.secretary;
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

    public SecretaryDTO createSecretary(Long userId, Long centreId) {

        // Step 1 — check this user isn't already a secretary
        if (secretaryRepository.findByUser_Id(userId).isPresent()) {
            throw new DuplicateResourceException("User " + userId + " is already a secretary");
        }

        // Step 2 — fetch dependencies
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new ResourceNotFoundException("Centre not found with id: " + centreId));

        // Step 3 — business rule: user must have SECRETAIRE role
        if (user.getRole() != User.Role.SECRETAIRE) {
            throw new IllegalArgumentException("User must have SECRETAIRE role to be a secretary");
        }

        // Step 4 — build and save
        secretary newSecretary = new secretary();
        newSecretary.setUser(user);
        newSecretary.setCentre(centre);

        return toDTO(secretaryRepository.save(newSecretary));
    }

    // --- DELETE ---

    public void deleteSecretary(Long id) {
        if (!secretaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Secretary not found with id: " + id);
        }
        secretaryRepository.deleteById(id);
    }

    // --- HELPERS ---

    private secretary findOrThrow(Long id) {
        return secretaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Secretary not found with id: " + id));
    }

    // --- DTO CONVERSION ---

    public SecretaryDTO toDTO(secretary s) {
        return new SecretaryDTO(
                s.getId(),
                s.getUser().getId(),
                s.getUser().getFullName(),
                s.getUser().getEmail(),
                s.getCentre().getId(),
                s.getCentre().getName()
        );
    }
}
