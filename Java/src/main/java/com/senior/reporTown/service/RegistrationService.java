package com.senior.reporTown.service;

import com.senior.reporTown.auth.ApplicationUser;
import com.senior.reporTown.auth.ApplicationUserDaoService;
import com.senior.reporTown.request.RegistrationRequest;
import com.senior.reporTown.security.UserRole;
import com.senior.reporTown.util.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ApplicationUserDaoService applicationUserDaoService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String role = request.getRole();
        UserRole userRole;
        if (role.equals("ADMIN")) {
            userRole = UserRole.ADMIN;
        }
        else if (role.equals("CITIZEN")){
            userRole = UserRole.CITIZEN;
        }
        else if (role.equals("INSTITUTION")) {
            userRole = UserRole.INSTITUTION;
        }
        else if (role.equals("OFFICIAL")) {
            userRole = UserRole.OFFICIAL;
        }
        else {
            throw new IllegalStateException("role not valid");
        }

        return applicationUserDaoService.signUpUser(
                new ApplicationUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getUsername(),
                        request.getPassword(),
                        userRole,
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}
