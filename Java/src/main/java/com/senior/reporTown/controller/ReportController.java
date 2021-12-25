package com.senior.reporTown.controller;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Report;
import com.senior.reporTown.request.ReportRequest;
import com.senior.reporTown.service.ReportService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    @PostMapping("/postReport")
    public String postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, @RequestBody ReportRequest request) {
        return reportService.postReport(authenticatedUser,request);
    }
}
