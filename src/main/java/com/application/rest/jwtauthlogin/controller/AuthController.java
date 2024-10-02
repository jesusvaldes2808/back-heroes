package com.application.rest.jwtauthlogin.controller;

import com.application.rest.jwtauthlogin.dto.request.LoginRequestDto;
import com.application.rest.jwtauthlogin.dto.request.RegisterRequestDto;
import com.application.rest.jwtauthlogin.dto.response.AuthResponse;
import com.application.rest.jwtauthlogin.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" )
public class AuthController {

    private final  AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDto loginRequest){

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDto registerRequestDto){


        try {

            AuthResponse authResponse = authService.registerUser(registerRequestDto);
            return    ResponseEntity.ok(authResponse);

        }catch (DataIntegrityViolationException e){

            AuthResponse errorResponse = new AuthResponse("error", "El usuario ya existe.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
}
