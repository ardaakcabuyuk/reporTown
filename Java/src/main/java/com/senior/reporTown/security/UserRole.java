package com.senior.reporTown.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.senior.reporTown.security.UserPermission.*;

//todo add more permissions
public enum UserRole {
    CITIZEN(Sets.newHashSet(CITIZEN_READ, REPORT_READ, REPORT_WRITE)),
    INSTITUTION(Sets.newHashSet(CITIZEN_READ, OFFICIAL_WRITE, REPORT_READ)),
    OFFICIAL(Sets.newHashSet(CITIZEN_READ, REPORT_READ, REPORT_WRITE));

    private final Set<UserPermission> permissions;

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
