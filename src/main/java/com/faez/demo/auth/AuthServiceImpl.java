package com.faez.demo.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.faez.demo.exceptions.ApiRequestException;
import com.faez.demo.role.Role;
import com.faez.demo.user.User;
import com.faez.demo.user.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.faez.demo.utils.constants.AppConfig.ACCESS_TOKEN_EXPIRATION;
import static com.faez.demo.utils.constants.AppConfig.JWT_ALGORITHM;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {
    private final UserServiceImpl userService;


    @Override
    public void handleRefreshToken(String authorizationHeader, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            JWTVerifier verifier = JWT.require(JWT_ALGORITHM).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            User user = userService.getUser(username);

            String issuer = request.getRequestURL().toString();
            String access_token = createAccessToken(user, issuer);

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
        try {
            return userService.saveUser(user);
        } catch (Exception e) {
            if (e.getMessage().contains("[user_email_unique]")) {
                throw new ApiRequestException("Email Exist!");
            } else if (e.getMessage().contains("[user_username_unique]")) {
                throw new ApiRequestException("Username Exist!");
            } else {
                throw new ApiRequestException(e.getMessage());
            }
        }
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

    private String createAccessToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(ACCESS_TOKEN_EXPIRATION)
                .withIssuer(issuer)
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(JWT_ALGORITHM);
    }

}
