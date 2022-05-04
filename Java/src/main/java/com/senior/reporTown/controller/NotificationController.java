package com.senior.reporTown.controller;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Notification;
import com.senior.reporTown.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class NotificationController {
    NotificationService notificationService;

    @GetMapping("/notification/fetch")
    public ResponseEntity<List<Notification>> fetch(@AuthenticationPrincipal ApplicationUser user) {
        return new ResponseEntity<>(notificationService.fetch(user.getId()), HttpStatus.OK);
    }
}
