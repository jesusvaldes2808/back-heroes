package com.application.rest.jwtauthlogin.services.impl;

import com.application.rest.jwtauthlogin.dao.UserRepository;
import com.application.rest.jwtauthlogin.dto.request.UserRequestDto;
import com.application.rest.jwtauthlogin.dto.response.UserResponseDto;
import com.application.rest.jwtauthlogin.enums.Role;
import com.application.rest.jwtauthlogin.model.User;
import com.application.rest.jwtauthlogin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {

        User user = User.builder()
                .id(userRequestDto.getId())
                .name(userRequestDto.getName())
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .role(Role.USER)
                .build();
        userRepository.updateUser(user.getId(), user.getName(), user.getUsername(), user.getPassword());

        return new UserResponseDto("El usuario se actualizó satisfactoriamente");

    }

    @Override
    public UserRequestDto getUserByID(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {

            return UserRequestDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .build();

        }
        return null;
    }
}
