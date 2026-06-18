package com.ProjetILEIC.ILIEC.service;

import com.ProjetILEIC.ILIEC.dto.CreateUserRequestDTO;
import com.ProjetILEIC.ILIEC.dto.UserDTO;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.DuplicateResourceException;
import com.ProjetILEIC.ILIEC.exception.ResourceNotFoundException;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    public UserDTO createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already in use: " + user.getEmail());
        }
        // Always hash the password before saving — never store plain text
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return toDTO(userRepository.save(user));
    }

    // --- UPDATE ---

    public UserDTO updateUser(Long id, User updated) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existing.setFullName(updated.getFullName());
        existing.setRole(updated.getRole());
        existing.setIsActive(updated.getIsActive());

        // Email change — check it isn't taken by someone else
        if (!existing.getEmail().equals(updated.getEmail())) {
            if (userRepository.findByEmail(updated.getEmail()).isPresent()) {
                throw new DuplicateResourceException("Email already in use: " + updated.getEmail());
            }
            existing.setEmail(updated.getEmail());
        }

        return toDTO(userRepository.save(existing));
    }

    // --- DELETE ---

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // handle the DTO and convert it into a User entity
    public UserDTO createUserFromDTO(CreateUserRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already in use: " + dto.getEmail());
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        // Hash the raw password from DTO
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);

        return toDTO(userRepository.save(user));
    }


    // --- DTO CONVERSION ---

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive(),
                user.getCreatedAt()
        );
    }
}
