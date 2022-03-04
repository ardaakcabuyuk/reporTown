package com.senior.reporTown.controller;

import com.senior.reporTown.model.ApplicationUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from reporTown!";
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
