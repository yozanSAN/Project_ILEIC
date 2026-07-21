package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.UserDTO;
import com.ProjetILEIC.ILIEC.dto.UserRequestDTO;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // --- READ ---
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return toDTO(user);
    }

    // --- CREATE ---

    public UserDTO createUser(UserRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already in use: " + dto.getEmail());
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());
        user.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);

        return toDTO(userRepository.save(user));
    }

    // --- UPDATE ---
    public UserDTO updateUser(Long id, UserRequestDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existing.setFullName(dto.getFullName());
        existing.setRole(dto.getRole());
        if (dto.getIsActive() != null) {
            existing.setIsActive(dto.getIsActive());
        }

        // Only update password if a new one is explicitly provided in the request payload
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }

        // Check if email modification creates a conflict with another user
        if (!existing.getEmail().equals(dto.getEmail())) {
            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new DuplicateResourceException("Email already in use: " + dto.getEmail());
            }
            existing.setEmail(dto.getEmail());
        }

        return toDTO(userRepository.save(existing));
    }

    //DEACTIVATE A USER
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setIsActive(false);
        userRepository.save(user);
    }

    //ACTIVATE A USER
    public void activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setIsActive(true);
        userRepository.save(user);
    }

    // --- DTO CONVERSION ---
    public UserDTO toDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getPhone(),
                user.getIsActive(),
                user.getCreatedAt()
        );
    }
}