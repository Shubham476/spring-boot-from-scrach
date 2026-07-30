package com.example.springSecurityApp.controllers;

import com.example.springSecurityApp.dto.LoginDTO;
import com.example.springSecurityApp.dto.SignUpDTO;
import com.example.springSecurityApp.dto.UserDTO;
import com.example.springSecurityApp.services.AuthService;
import com.example.springSecurityApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);
        return ResponseEntity.ok(token);
    }
}
