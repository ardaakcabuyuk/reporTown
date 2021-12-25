package com.senior.reporTown.model;

import com.senior.reporTown.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Citizen extends ApplicationUser {
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String bio;
    private float score;

    public Citizen(String firstName,
                   String lastName,
                   String email,
                   String username,
                   String password,
                   UserRole role,
                   boolean isAccountNonExpired,
                   boolean isAccountNonLocked,
                   boolean isCredentialsNonExpired,
                   boolean isEnabled) {
        super(email, username, password, role, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.firstName = firstName;
        this.lastName = lastName;
        profilePicture = "";
        bio = "";
        score = (float) 0.0;
    }
}
