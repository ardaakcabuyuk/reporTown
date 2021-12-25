package com.senior.reporTown.controller;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Report;
import com.senior.reporTown.request.ReportRequest;
import com.senior.reporTown.service.ReportService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    @PostMapping("/postReport")
    public Report postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, @RequestBody ReportRequest request) {
        return reportService.postReport(authenticatedUser,request);
    }

    /*
    //parameter allow us to assign profile image to ("profileId")
    @PostMapping(
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadReportImage(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                       @RequestParam("file") MultipartFile file ){

        reportService.uploadReportImage(authenticatedUser,file);

    }*/

}
