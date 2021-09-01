package com.faez.demo.controllers;

import com.faez.demo.services.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.faez.demo.routes.ApiRoutes.REFRESH_TOKEN_API;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthServiceImpl authService;

    @GetMapping(REFRESH_TOKEN_API)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authService.handleRefreshToken(authorizationHeader, request, response);
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
