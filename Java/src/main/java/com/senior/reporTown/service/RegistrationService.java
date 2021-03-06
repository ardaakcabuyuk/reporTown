package com.senior.reporTown.service;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Citizen;
import com.senior.reporTown.model.Institution;
import com.senior.reporTown.model.Official;
import com.senior.reporTown.request.RegistrationRequest;
import com.senior.reporTown.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public ApplicationUser register(RegistrationRequest request) {
        ApplicationUser registeredUser = null;
        String role = request.getRole();
        try {
            if (role.equals("CITIZEN")) {
                registeredUser = new Citizen(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getUsername(),
                        request.getPassword(),
                        UserRole.CITIZEN,
                        true,
                        true,
                        true,
                        true,
                        false
                );
            } else if (role.equals("INSTITUTION")) {
                registeredUser = new Institution(
                        request.getInstitutionName(),
                        request.getEmail(),
                        request.getUsername(),
                        request.getPassword(),
                        UserRole.INSTITUTION,
                        request.getApprovalDocument(),
                        new ArrayList<String>(),
                        true,
                        true,
                        true,
                        true,
                        request.getCountry(),
                        request.getCity(),
                        request.getEmployees(),
                        request.getEmployeeIds()
                );
            } else if (role.equals("OFFICIAL")) {
                registeredUser = new Official(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getUsername(),
                        request.getPassword(),
                        UserRole.OFFICIAL,
                        request.getPosition(),
                        true,
                        true,
                        true,
                        true,
                        ""
                );
            } else {
                throw new IllegalStateException("role not valid");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return userService.signUpUser(registeredUser);
    }
}
