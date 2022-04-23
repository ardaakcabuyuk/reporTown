package com.senior.reporTown.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    // Common
    private final String email;
    private final String username;
    private final String password;
    private final String role;

    // Citizen & Official
    private final String firstName;
    private final String lastName;

    // Official
    private final String position;

    // Institution
    private String institutionName;
    private String approvalDocument;
    private String positions;
    private String country;
    private String city;
}
