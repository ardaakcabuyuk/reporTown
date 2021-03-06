package com.senior.reporTown.controller;

import com.senior.reporTown.model.*;
import com.senior.reporTown.request.AssignOfficialRequest;
import com.senior.reporTown.request.CommentRequest;
import com.senior.reporTown.request.ReportRequest;
import com.senior.reporTown.request.SolutionRequest;
import com.senior.reporTown.service.ReportService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    @PostMapping("/report/post")
    public Report postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, @RequestBody ReportRequest request) {
        return reportService.postReport(authenticatedUser,request);
    }

    @PostMapping("/report/{id}/edit")
    public Report editReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, @RequestBody ReportRequest request, @PathVariable ObjectId id) {
        return reportService.editReport(id, request.getDescription(), request.getCategory());
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getReport(@PathVariable ObjectId id) {
        Report report = reportService.getReport(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        if (report == null) {
            response.put("msg", "This report does not exist!");
            status = HttpStatus.NOT_FOUND;
        }
        else {
            response.put("msg", "success");
            response.put("report", report);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, status);
    }

    @PatchMapping("/report/{reportId}/upvote")
    public ResponseEntity<Object> upvoteReport(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                               @PathVariable ObjectId reportId) {
        int currentUpvoteCount = reportService.upvoteReport(authenticatedUser.getId(), reportId);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        if (currentUpvoteCount == -1) {
            response.put("msg", "This report does not exist!");
            status = HttpStatus.NOT_FOUND;
        }
        else {
            response.put("msg", "success");
            response.put("upvotes", currentUpvoteCount);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, status);
    }


    @PostMapping("/report/{reportId}/comment")
    public ResponseEntity<Object> commentToReport(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                                  @RequestBody CommentRequest request,
                                                  @PathVariable ObjectId reportId) {
        Citizen user = (Citizen) authenticatedUser;
        Comment comment = reportService.commentToReport(authenticatedUser.getId(), reportId, request.getText(),user.getFirstName(), user.getLastName(), authenticatedUser.getUsername());
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        if (comment == null) {
            response.put("msg", "This report or user does not exist!");
            status = HttpStatus.NOT_FOUND;
        }
        else {
            response.put("msg", "success");
            response.put("comment", comment);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/report/{reportId}/comment/{commentId}")
    public ResponseEntity<Object> deleteComment(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                                @PathVariable ObjectId reportId,
                                                @PathVariable ObjectId commentId) {
        Report report = reportService.deleteComment(reportId, commentId);
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "success");
        response.put("report", report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/report/{id}/delete")
    public ResponseEntity<Object> deleteReport(@PathVariable ObjectId id) {
        reportService.deleteReport(id);
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/feed")
    public List<Report> getAllReports(){
        return reportService.getAllReports();
    }


    @GetMapping("/officialFeedResolvedReports")
    public List<Report> getAllSolvedReportsByOfficial(@AuthenticationPrincipal ApplicationUser authenticatedUser){
        return reportService.getAllSolvedReportsOfficial(authenticatedUser.getId());
    }

    @GetMapping("/officialFeedUnresolvedReports")
    public List<Report> getAllUnsolvedReportsByOfficial(@AuthenticationPrincipal ApplicationUser authenticatedUser){
        //System.out.println("in controller");
        return reportService.getAllUnsolvedReportsOfficial(authenticatedUser.getId());
    }

    @GetMapping("/officialFeedNotResolvedByCitizen")
    public List<Report> officialFeedNotResolvedByCitizen(@AuthenticationPrincipal ApplicationUser authenticatedUser){
        //System.out.println("in controller");
        return reportService.officialFeedNotResolvedByCitizen(authenticatedUser.getId());
    }

    @GetMapping("/officialFeedReportsWithNoSolution")
    public List<Report> officialFeedReportsWithNoSolution(@AuthenticationPrincipal ApplicationUser authenticatedUser){
        //System.out.println("in controller");
        return reportService.officialFeedReportsWithNoSolution(authenticatedUser.getId());
    }



    @PostMapping("/report/{reportId}/solution/post")
    public ResponseEntity<Object> postSolution(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                              @RequestBody SolutionRequest request,
                                              @PathVariable ObjectId reportId){
        Solution solution = reportService.postSolution(authenticatedUser.getId(),reportId, request.getDescription());
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        response.put("msg", "success");
        status = HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/report/{reportId}/solution/approve")
    public ResponseEntity<Object> approveSolution(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                               @PathVariable ObjectId reportId){
        reportService.approveSolution(authenticatedUser.getId(), reportId);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        response.put("msg", "success");
        status = HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/report/{reportId}/solution/mark")
    public ResponseEntity<Object> markSolutionAsSolved(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                                  @PathVariable ObjectId reportId){
        reportService.markAsSolved(authenticatedUser.getId(), reportId);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        response.put("msg", "success");
        status = HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/assignOfficial/{officialId}/toReport")
    public Report assignOfficialToReport(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                                 @PathVariable ObjectId officialId, @RequestBody AssignOfficialRequest request){

        Report report = reportService.assignOfficialToReport(officialId,request.getReportId());
        return report;

    }

    @GetMapping("/officialFeed")
    public List<Report> getReportsOfficial(@AuthenticationPrincipal ApplicationUser authenticatedUser){
        List<Report> reportsOfficial = reportService.getReportsByOfficial(authenticatedUser.getId());
        return reportsOfficial;
    }

    @GetMapping("/report/{reportId}/solution/reject")
    public ResponseEntity<Object> rejectReport(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                              @PathVariable ObjectId reportId){
        Report report = reportService.rejectSolution(authenticatedUser.getId(), reportId);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        response.put("msg", "success");
        status = HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/report/trending")
    public ResponseEntity<List<Report>> getTrendingReports() {
        return new ResponseEntity<>(reportService.getTrendingReports(), HttpStatus.OK);
    }

}
