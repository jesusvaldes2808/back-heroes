package com.application.rest.jwtauthlogin.services.impl;

import com.application.rest.jwtauthlogin.dao.UserRepository;
import com.application.rest.jwtauthlogin.dto.request.LoginRequestDto;
import com.application.rest.jwtauthlogin.dto.request.RegisterRequestDto;
import com.application.rest.jwtauthlogin.dto.response.AuthResponse;
import com.application.rest.jwtauthlogin.enums.Role;
import com.application.rest.jwtauthlogin.model.User;
import com.application.rest.jwtauthlogin.services.AuthService;
import com.application.rest.jwtauthlogin.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequestDto loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse registerUser(RegisterRequestDto request) {
        try {
            User user = User.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException("El usuario ya existe.");
        }
    }
}
