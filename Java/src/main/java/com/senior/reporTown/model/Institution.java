package com.senior.reporTown.model;

import com.senior.reporTown.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Institution extends ApplicationUser {
    private String institutionName;
    private String approvalDocument;
    private List<String> positions;
    private String profilePicture;
    private String bio;
    private float score;
    private String country;
    private String city;
    private List<Official> employees;

    public Institution(String institutionName,
                       String email,
                       String username,
                       String password,
                       UserRole role,
                       String approvalDocument,
                       List<String> positions,
                       boolean isAccountNonExpired,
                       boolean isAccountNonLocked,
                       boolean isCredentialsNonExpired,
                       boolean isEnabled,
                       String country,
                       String city,
                       List<Official> employees) {
        super(email, username, password, role, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.institutionName = institutionName;
        this.approvalDocument = approvalDocument;
        this.positions = new ArrayList<String>();
        this.country = country;
        this.city = city;
        profilePicture = "";
        bio = "";
        score = (float) 0.0;
        this.employees = new ArrayList<Official>();
    }
}