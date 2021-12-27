package com.senior.reporTown.model;

import com.senior.reporTown.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
                       boolean isEnabled) {
        super(email, username, password, role, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.institutionName = institutionName;
        this.approvalDocument = approvalDocument;
        this.positions = positions;
        profilePicture = "";
        bio = "";
        score = (float) 0.0;
    }
}