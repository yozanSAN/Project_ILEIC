package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.UserDTO;
import com.ProjetILEIC.ILIEC.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
    @GetMapping("/api/students")
    @PreAuthorize("hasAnyAuthority('SECRETAIRE')")
    public List<UserDTO> getAllStudents() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("ACTIVE USER: " + auth.getName());
        System.out.println("ACTIVE AUTHORITIES: " + auth.getAuthorities());
        return userService.getAllUsers();
    }
}

