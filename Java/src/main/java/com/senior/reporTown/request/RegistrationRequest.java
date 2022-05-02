package com.senior.reporTown.request;

import com.senior.reporTown.model.Official;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.List;

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
    private final ObjectId institutionId;

    // Institution
    private String institutionName;
    private String approvalDocument;
    private String positions;
    private String country;
    private String city;
    private List<Official> employees;

}
