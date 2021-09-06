package com.faez.demo.auth;

import com.faez.demo.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import static com.faez.demo.routes.ApiRoute.REFRESH_TOKEN_API;
import static com.faez.demo.routes.ApiRoute.REGISTER_API;
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


    @PostMapping(REGISTER_API)
    public ResponseEntity<User> registerUser(@Validated @RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(REGISTER_API).toUriString());
        return ResponseEntity.created(uri).body(authService.registerUser(user));
    }
}
