package com.senior.reporTown.controller;

import com.senior.reporTown.request.ReportRequest;
import com.senior.reporTown.service.PostReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReportController {

    private PostReportService reportService;
    @PostMapping("/postReport")
    public String postReport(@RequestBody ReportRequest request) {
        return reportService.postReport(request);
    }
}
