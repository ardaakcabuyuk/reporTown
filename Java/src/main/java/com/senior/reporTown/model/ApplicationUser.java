package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.senior.reporTown.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "users")
public class ApplicationUser implements UserDetails {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    private String email;
    private String username;
    private String password;
    /*@ElementCollection(targetClass = GrantedAuthority.class)
    private Set<? extends GrantedAuthority> authorities;*/
    //@Enumerated
    private UserRole role;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;


    public ApplicationUser(String email,
                           String username,
                           String password,
                           UserRole role,
                           //Set<? extends GrantedAuthority> authorities,
                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.email = email;
        //this.authorities = role.getGrantedAuthorities();
        this.role = role;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;

    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    public ObjectId getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
