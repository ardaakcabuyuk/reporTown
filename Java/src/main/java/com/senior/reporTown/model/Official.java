package com.senior.reporTown.model;

import com.senior.reporTown.security.UserRole;

public class Official extends ApplicationUser {
    private String firstName;
    private String lastName;
    private String position;
    private String profilePicture;
    private String bio;
    private float score;

    public Official(String firstName,
                   String lastName,
                   String email,
                   String username,
                   String password,
                   UserRole role,
                   String position,
                   boolean isAccountNonExpired,
                   boolean isAccountNonLocked,
                   boolean isCredentialsNonExpired,
                   boolean isEnabled) {
        super(email, username, password, role, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        profilePicture = "";
        bio = "";
        score = (float) 0.0;
    }
}
