package com.ProjetILEIC.ILIEC.security;

import com.ProjetILEIC.ILIEC.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final JwtUtil jwtUtil;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, AuthEntryPointJwt authEntryPointJwt, JwtUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPointJwt = authEntryPointJwt;
        this.jwtUtil = jwtUtil;
    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtil, customUserDetailsService);
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF because JWT tokens protect against it naturally
                .csrf(csrf -> csrf.disable())

                // 2. Handle unauthorized exceptions cleanly with your custom EntryPoint
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt))

                // 3. Set session creation policy to stateless (no HTTP sessions)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 4. Define route access rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**","/welcome").permitAll() // Let anyone access signin/signup endpoints
                        .anyRequest().authenticated()               // Everything else requires a valid token
                );

        // 5. Add your custom JWT token verification filter before Spring's default username/password checker
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
