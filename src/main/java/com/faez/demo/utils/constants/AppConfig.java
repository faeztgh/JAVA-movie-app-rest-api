package com.faez.demo.utils.constants;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class AppConfig {

    public static final String JWT_SECRET = "Sh!t";

    public static final Date ACCESS_TOKEN_EXPIRATION = new Date(System.currentTimeMillis() + 60 * 1000 * 60 * 24 * 2);

    public static final Date REFRESH_TOKEN_EXPIRATION = new Date(System.currentTimeMillis() + 60 * 1000 * 60 * 24 * 4);

    public static final Algorithm JWT_ALGORITHM = Algorithm.HMAC256(JWT_SECRET.getBytes());

    // Upload
    public static final String UPLOAD_AVATAR_DIR = "/src/main/resources/uploads/avatar/";
    public static final int MAX_UPLOAD_AVATAR_SIZE = 1024 * 1024 * 3; //3MB

}
