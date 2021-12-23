package com.senior.reporTown.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "REPORTOWN";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from reporTown!";
    }

    @GetMapping("/banCitizen/{id}")
    public String banUser(@PathVariable Long id) {
        return "Citizen " + id + " is banned.";
    }

    @PostMapping("/postReport")
    @PreAuthorize("hasAuthority('report:write')")
    public String postReport(@RequestBody String text) {
        return "Post: " + text;
    }

    @PostMapping("/registerOfficial")
    public String registerOfficial(@RequestBody String username) {
        return "Official: " + username;
    }
}
