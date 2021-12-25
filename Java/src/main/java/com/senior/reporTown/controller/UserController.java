package com.senior.reporTown.controller;

import com.senior.reporTown.model.Report;
import com.senior.reporTown.response.ProfileResponse;
import com.senior.reporTown.response.UserInfoResponse;
import com.senior.reporTown.service.ReportService;
import com.senior.reporTown.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private ReportService reportService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable ObjectId userId) {
        List<Report> reports = reportService.getReportsByUser(userId);
        UserDetails user = userService.findUserById(userId);
        return new ResponseEntity(new ProfileResponse(true, user, reports), HttpStatus.OK);
    }
}
