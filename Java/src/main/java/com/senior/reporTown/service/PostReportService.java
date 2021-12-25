package com.senior.reporTown.service;

import com.senior.reporTown.model.Report;
import com.senior.reporTown.repository.ReportRepository;
import com.senior.reporTown.request.ReportRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostReportService {

    private final ReportService reportService;

    public String postReport(ReportRequest request) {
        return reportService.postReport(
                new Report(
                      request.getDescription(),
                      request.getCategory()
                )
        );

    }
}
