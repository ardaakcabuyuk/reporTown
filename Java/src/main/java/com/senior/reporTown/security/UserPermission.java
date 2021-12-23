package com.senior.reporTown.security;

public enum UserPermission {
    CITIZEN_READ("citizen:read"),
    CITIZEN_WRITE("citizen:write"),
    REPORT_READ("report:read"),
    REPORT_WRITE("report:write"),
    OFFICIAL_WRITE("official:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
