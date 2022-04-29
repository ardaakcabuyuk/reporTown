package com.senior.reporTown.controller;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Citizen;
import com.senior.reporTown.model.Report;
import com.senior.reporTown.response.ProfileResponse;
import com.senior.reporTown.response.UserInfoResponse;
import com.senior.reporTown.security.UserRole;
import com.senior.reporTown.service.ReportService;
import com.senior.reporTown.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        ApplicationUser user = (ApplicationUser) userService.findUserById(userId);
        List<Report> reports = null;
        if (user.getRole() == UserRole.CITIZEN)
            reports = reportService.getReportsByUser(userId);
        else if (user.getRole() == UserRole.INSTITUTION)
            reports = reportService.getReportsByInstitution(userId);
        return new ResponseEntity(new ProfileResponse(true, user, reports), HttpStatus.OK);
    }

    @PostMapping("/profile/{userId}/volunteer")
    public ResponseEntity<ProfileResponse> changeVolunteer(@PathVariable ObjectId userId){

        userService.changeVolunteer(userId);
        return getProfile(userId);
    }

    @PostMapping("/profile/{userId}/updateBio")
    public ResponseEntity<ProfileResponse> updateBio(@PathVariable ObjectId userId){

        userService.updateBio(userId);
        return getProfile(userId);
    }
}
