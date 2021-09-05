package com.faez.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.faez.demo.models.Role;
import com.faez.demo.models.User;
import com.faez.demo.services.interfaces.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.faez.demo.common.constants.Constant.JWT_SECRET;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void handleRefreshToken(String authorizationHeader, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            User user = userService.getUser(username);

            Date accessTokenExpiration = new Date(System.currentTimeMillis() + 10 * 160 * 1000);
            String issuer = request.getRequestURL().toString();
            String access_token = createAccessToken(user, issuer, algorithm, accessTokenExpiration);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);


        } catch (Exception exception) {
            handleAuthError(exception, response);
        }
    }

    @Override
    public User registerUser(User user) {
        return userService.saveUser(user);
    }


    public void handleAuthError(Exception exception, HttpServletResponse response) throws IOException {
        log.error("Error Logging in: {}", exception.getMessage());
        response.setHeader("error", exception.getMessage());
        response.setStatus(SC_UNAUTHORIZED);
//        response.sendError(FORBIDDEN.value(), exception.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

    private String createAccessToken(User user, String issuer, Algorithm algorithm, Date expirationDate) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expirationDate)
                .withIssuer(issuer)
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
    }

}
