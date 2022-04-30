package com.senior.reporTown.controller;

import com.senior.reporTown.model.*;
import com.senior.reporTown.request.EditProfileRequest;
import com.senior.reporTown.request.RegistrationRequest;
import com.senior.reporTown.response.ProfileResponse;
import com.senior.reporTown.security.UserRole;
import com.senior.reporTown.service.ReportService;
import com.senior.reporTown.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ProfileResponse> updateBio(@AuthenticationPrincipal ApplicationUser authenticatedUser,@RequestBody EditProfileRequest request,
                                                     @PathVariable ObjectId userId){
        userService.updateBio(userId,request.getBio());
        return getProfile(userId);
    }

    @PostMapping("/institution/addOfficial")
    public ResponseEntity<ProfileResponse> addOfficial(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                                       @RequestBody RegistrationRequest request){

        Institution institution = (Institution) authenticatedUser;
        Official official = userService.addOfficial(institution,request);
        userService.signUpUser(official);
        return getProfile(authenticatedUser.getId());
    }


}
