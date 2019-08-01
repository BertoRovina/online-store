package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.dto.EmailDto;
import com.hrovina.onlinestore.security.JWTUtil;
import com.hrovina.onlinestore.security.UserSS;
import com.hrovina.onlinestore.services.AuthService;
import com.hrovina.onlinestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse resp){
        UserSS userSS = UserService.authenticated();
        String token = jwtUtil.generateToken(userSS.getUsername());
        resp.addHeader("Authorization", "Bearer " + token);
        resp.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto emailDto){
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
