package com.senior.reporTown.service;

import com.senior.reporTown.request.ReportRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {

    private final UserService userService;

    public String postReport(ReportRequest request) {

        //return ApplicationUserDaoService
        return null;
    }
}
