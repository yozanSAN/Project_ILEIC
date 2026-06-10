package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.AuthResponseDTO;
import com.ProjetILEIC.ILIEC.dto.LoginRequestDTO;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import com.ProjetILEIC.ILIEC.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO requestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getEmail(),
                        requestDTO.getPassword()
                )
            );
            // 2. Extract user details from the authentication principal
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. Generate the token string
            String token = jwtUtil.generateToken(userDetails.getUsername());

            //4.Find the user by email and return an exception if not found
            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not Found!!"));

             // 4. Return a clean userDTO object instead of a string
            return ResponseEntity.ok(new AuthResponseDTO(token, user.getEmail() ,user.getRole()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: invalid email or password");
        }
    }


}
