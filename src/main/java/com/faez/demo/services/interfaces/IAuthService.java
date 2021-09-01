package com.faez.demo.services.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAuthService {
    void handleRefreshToken(String authorizationHeader, HttpServletRequest request, HttpServletResponse response) throws IOException;

    void handleAuthError(Exception exception, HttpServletResponse response) throws IOException;

//    String createAccessToken();
//
//    String createRefreshToken();


}
