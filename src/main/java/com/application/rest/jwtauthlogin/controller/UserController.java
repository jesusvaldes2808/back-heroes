package com.application.rest.jwtauthlogin.controller;


import com.application.rest.jwtauthlogin.dto.request.UserRequestDto;
import com.application.rest.jwtauthlogin.dto.response.UserResponseDto;
import com.application.rest.jwtauthlogin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {

        UserRequestDto userRequest = userService.getUserByID(id);

        if(userRequest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRequest);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequest)
    {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

}
