package com.application.rest.jwtauthlogin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {


    private Long id;
    private String name;
    private String username;
    private String password;
}
