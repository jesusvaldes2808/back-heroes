package com.application.rest.jwtauthlogin.services;

import com.application.rest.jwtauthlogin.dto.request.UserRequestDto;
import com.application.rest.jwtauthlogin.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    UserRequestDto getUserByID(Long id);
}
