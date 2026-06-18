package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.CreateUserRequestDTO;
import com.ProjetILEIC.ILIEC.dto.UserDTO;
import com.ProjetILEIC.ILIEC.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users" )
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


     //Create a new user account.
     //Gated to ADMIN and SECRETAIRE roles.
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequestDTO requestDTO) {
        UserDTO createdUser = userService.createUserFromDTO(requestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    // endpoint to list users.
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
