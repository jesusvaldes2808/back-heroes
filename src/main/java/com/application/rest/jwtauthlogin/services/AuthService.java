package com.application.rest.jwtauthlogin.services;

import com.application.rest.jwtauthlogin.dto.request.LoginRequestDto;
import com.application.rest.jwtauthlogin.dto.request.RegisterRequestDto;
import com.application.rest.jwtauthlogin.dto.response.AuthResponse;

public interface AuthService {

     AuthResponse login (LoginRequestDto loginRequest);

     AuthResponse registerUser (RegisterRequestDto registerRequestDto);
}
