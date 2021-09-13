package com.faez.movie.enums;

public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    SUPER_ADMIN_READ("superAdmin:read"),
    SUPER_ADMIN_WRITE("superAdmin:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
