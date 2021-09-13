package com.faez.movie.auth;

import com.faez.movie.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAuthService {
    void handleRefreshToken(String authorizationHeader, HttpServletRequest request, HttpServletResponse response) throws IOException;

    User registerUser(User user);
}
