package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import com.ProjetILEIC.ILIEC.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPasswordHash()
                )
            );
         // 2. Extract user details from the authentication principal
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. Generate the token string
            String token = jwtUtil.generateToken(userDetails.getUsername());

             // 4. Return the raw token back as plain text with user emial
            return "Token: " + token + " | Email: " + userDetails.getUsername();
        } catch (Exception e) {
            return "Authentication failed: Invalid email or school code." + e + e.getMessage();
        }
    }


}
