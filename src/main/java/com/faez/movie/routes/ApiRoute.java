package com.faez.movie.routes;

public class ApiRoute {

    // Config
    private static final String API_PREFIX = "/api";
    private static final String API_VERSION = "";
    private static final String API_BASE = API_PREFIX + API_VERSION;

    // Docs
    public static final String JSON_DOCS_API = API_BASE + "/docs";
    public static final String SWAGGER_DOCS_API = API_BASE + "/swagger-ui";

    // Auth
    public static final String AUTH_API = API_BASE + "/auth";
    public static final String LOGIN_API = API_BASE + "/auth/login";
    public static final String REGISTER_API = API_BASE + "/auth/register";
    public static final String REFRESH_TOKEN_API = API_BASE + "/auth/token/refresh";
    public static final String RESET_PASSWORD_API = API_BASE + "/auth/reset-password";
    public static final String VALIDATE_RESET_PASSWORD_TOKEN_API = API_BASE + "/auth/reset-password/validate-token";
    public static final String NEW_PASSWORD_API = API_BASE + "/auth/reset-password/new-password";

    // User
    public static final String USERS_API = API_BASE + "/users";
    public static final String GET_USER_BY_ID_API = API_BASE + "/users/{id}";
    public static final String UPLOAD_AVATAR_API = API_BASE + "/users/avatar";

    // Role
    public static final String ROLE_API = API_BASE + "/role";
    public static final String ADD_ROLE_TO_USER_API = API_BASE + "/role/addRoleToUser";

    // Movie
    public static final String MOVIES_API = API_BASE + "/movies";
    public static final String GET_MOVIE_BY_ID_API = API_BASE + "/movies/{id}";
    public static final String UPDATE_MOVIE_API = API_BASE + "/movies/{id}";
    public static final String DELETE_MOVIE_API = API_BASE + "/movies/{id}";

    // Quote
    public static final String QUOTES_API = API_BASE + "/quotes";
    public static final String GET_QUOTE_BY_ID_API = API_BASE + "/quotes/{id}";
    public static final String UPDATE_QUOTE_API = API_BASE + "/quotes/{id}";
    public static final String DELETE_QUOTE_API = API_BASE + "/quotes/{id}";

}
