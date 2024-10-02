package com.application.rest.jwtauthlogin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    String token;

    private String status;
    private String message;

    public AuthResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
