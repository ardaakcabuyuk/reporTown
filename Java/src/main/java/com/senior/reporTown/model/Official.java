package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.senior.reporTown.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Official extends ApplicationUser {

    private String firstName;
    private String lastName;
    private String position;
    private String profilePicture;
    private String bio;
    private String institutionName;


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
                   boolean isEnabled,
                    String institutionName) {
        super(email, username, password, role, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        profilePicture = "";
        bio = "";
        this.institutionName = institutionName;

    }
}
