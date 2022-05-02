package com.senior.reporTown.controller;

import com.senior.reporTown.model.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    @PostMapping("/report/post")
    public Report postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, @RequestBody ReportRequest request) {
        return reportService.postReport(authenticatedUser,request);
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

    @GetMapping("/feed")
    public List<Report> getAllReports(){
        return reportService.getAllReports();
    }

    @GetMapping("/feed/solvedReports")
    public List<Report> getAllSolvedReports(){
        return reportService.getAllSolvedReports();
    }


    @PostMapping("/report/{reportId}/solve")
    public ResponseEntity<Object> solveReport(@AuthenticationPrincipal ApplicationUser authenticatedUser,
                                              @RequestBody SolutionRequest request,
                                              @PathVariable ObjectId reportId){
        Solution solution = reportService.solveReport(authenticatedUser.getId(),reportId, request.getDescription(), request.getFile());
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(solution == null ){
            response.put("msg", "success");
            //response.put("comment", comment);
            status = HttpStatus.OK;
        }
        else{
            response.put("msg", "This report is already solved or not exists");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, status);
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
