package com.ProjetILEIC.ILIEC.controller;

import com.ProjetILEIC.ILIEC.dto.AuthResponseDTO;
import com.ProjetILEIC.ILIEC.dto.LoginRequestDTO;
import com.ProjetILEIC.ILIEC.dto.auth.TokenDTO;
import com.ProjetILEIC.ILIEC.dto.auth.TokenRequestDTO;
import com.ProjetILEIC.ILIEC.entity.RefreshToken;
import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.exception.InvalidTokenException;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import com.ProjetILEIC.ILIEC.security.JwtUtil;
import com.ProjetILEIC.ILIEC.service.RefreshTokenService;
import jakarta.validation.Valid;
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
    private final RefreshTokenService refreshTokenService;

     //ENDPOINT: /api/auth/login
     //Purpose: Authenticates credentials and starts a dual-token user session.
    //Actions: Checks password, creates a short-lived access token, and generates
     //a long-lived refresh token saved in the database.
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO requestDTO) {
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
            String accessToken = jwtUtil.generateToken(userDetails.getUsername());

            //4.Find the user by email and return an exception if not found
            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not Found!!"));

            // 4. Create refresh token
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

             // 5. Return a clean userDTO object instead of a string
            return ResponseEntity.ok(new AuthResponseDTO(
                    accessToken,
                    refreshToken.getToken(),
                    user.getEmail() ,
                    user.getRole()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            // Add this import at the top if needed
// @Autowired private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

            // Re-throw exceptions explicitly managed by our global advisor, such as account deactivation
            if (e instanceof org.springframework.security.authentication.DisabledException) {
                throw e;
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: invalid email or password");
        }
    }

    /**
     * ENDPOINT: /api/auth/refresh
     * Purpose: Renews an expired access token without making the user re-type their password.
     * Actions: Looks up the incoming refresh token string, verifies it hasn't expired,
     *          and issues a brand-new access token.
     */
    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshAccessToken(@Valid @RequestBody TokenRequestDTO request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    // Generate a fresh short-lived access token using your existing jwtUtil
                    String newAccessToken = jwtUtil.generateToken(user.getEmail());

                    // Return using your preferred project TokenDTO layout
                    TokenDTO response = TokenDTO.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(requestRefreshToken)
                            .build();

                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new InvalidTokenException("Refresh token is not present in the database."));
    }

    /**
     * ENDPOINT: /api/auth/logout
     * Purpose: Terminates a user session securely.
     * Actions: Permanently deletes the active refresh token from the database,
     *          rendering it completely useless if intercepted.
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@Valid @RequestBody TokenRequestDTO request) {
        // Evict token to force session revocation
        refreshTokenService.deleteByToken(request.getRefreshToken());
        return ResponseEntity.ok("User logged out successfully.");
    }
}
