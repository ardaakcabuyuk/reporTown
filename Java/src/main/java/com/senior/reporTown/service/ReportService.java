package com.senior.reporTown.service;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Report;
import com.senior.reporTown.repository.ReportRepository;
import com.senior.reporTown.request.ReportRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public String postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, ReportRequest request) {
        reportRepository.save(new Report(
                request.getDescription(),
                request.getCategory(),
                request.getComments(),
                request.getUpvotes(),
                request.getLocation(),
                request.getImages(),
                authenticatedUser.get_id() )
        );
        return "report posted";
    }
}
