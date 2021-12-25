package com.senior.reporTown.controller;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.request.RegistrationRequest;
import com.senior.reporTown.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    @PostMapping("/register")
    public ApplicationUser register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
