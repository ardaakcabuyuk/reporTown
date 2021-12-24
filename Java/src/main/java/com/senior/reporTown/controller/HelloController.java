package com.senior.reporTown.controller;

import com.senior.reporTown.auth.ApplicationUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "REPORTOWN";
    }

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal ApplicationUser authenticatedUser) {
        return "Hello from reporTown " + authenticatedUser.getUsername() + "!";
    }

    @GetMapping("/banCitizen/{id}")
    public String banUser(@PathVariable Long id) {
        return "Citizen " + id + " is banned.";
    }

    @PostMapping("/registerOfficial")
    public String registerOfficial(@RequestBody String username) {
        return "Official: " + username;
    }
}
