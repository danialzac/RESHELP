package com.resace.backend.service;

import com.resace.backend.dto.AuthResponse;
import com.resace.backend.dto.LoginRequest;
import com.resace.backend.dto.RegisterRequest;
import com.resace.backend.dto.UserSummary;
import com.resace.backend.model.AppUser;
import com.resace.backend.model.Role;
import com.resace.backend.repository.UserRepository;
import com.resace.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        AppUser user = userRepository.save(
            AppUser.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build()
        );

        return buildResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        AppUser user = userRepository.findByEmail(request.email()).orElseThrow();
        return buildResponse(user);
    }

    private AuthResponse buildResponse(AppUser user) {
        String token = jwtService.generateToken(user);
        UserSummary userSummary = new UserSummary(user.getId(), user.getName(), user.getEmail(), user.getRole().name());
        return new AuthResponse(token, userSummary);
    }
}
