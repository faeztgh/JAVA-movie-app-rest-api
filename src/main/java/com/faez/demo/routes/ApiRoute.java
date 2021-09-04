package com.faez.demo.routes;

public class ApiRoute {

    // Config
    private static final String API_PREFIX = "/api";
    private static final String API_VERSION = "";
    private static final String API_BASE = API_PREFIX + API_VERSION;

    // Auth
    public static final String AUTH_API = API_BASE + "/auth";
    public static final String LOGIN_API = API_BASE + "/auth/login";
    public static final String REFRESH_TOKEN_API = API_BASE + "/auth/token/refresh";

    // User
    public static final String USERS_API = API_BASE + "/users";
    public static final String GET_USER_BY_ID_API = API_BASE + "/users/{id}";

    // Role
    public static final String ROLE_API = API_BASE + "/role";
    public static final String ADD_ROLE_TO_USER_API = API_BASE + "/role/addRoleToUser";

    // Movie
    public static final String MOVIES_API = API_BASE + "/movies";
    public static final String GET_MOVIE_BY_ID_API = API_BASE + "/movies/{id}";


}
