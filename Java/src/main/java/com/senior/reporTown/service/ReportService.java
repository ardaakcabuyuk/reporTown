package com.senior.reporTown.service;

import com.amazonaws.services.s3.AmazonS3;
import com.senior.reporTown.FileStore;
import com.senior.reporTown.buckets.BucketName;
import com.senior.reporTown.model.*;
import com.senior.reporTown.repository.ReportRepository;
import com.senior.reporTown.repository.UserRepository;
import com.senior.reporTown.request.ReportRequest;
import org.bson.types.ObjectId;
import org.apache.http.entity.ContentType;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import java.util.List;
import java.util.Collections;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    //private final FileStore fileStore;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository) { //,FileStore fileStore) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;

        //this.fileStore = fileStore;
    }

    public Report postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, ReportRequest request) {
        //String link = uploadReportImage(authenticatedUser, request.getFile());
        Institution institution = (Institution) userRepository.findById(request.getInstitutionId()).get();
        Report newReport = new Report(
                request.getDescription(),
                request.getCategory(),
                request.getLocation(),
                request.getReport_image_link(),
                request.getFile(),
                authenticatedUser.getId(),
                request.getInstitutionId(),
                request.getSolution(),
                request.getLongitude(),
                request.getLatitude(),
                authenticatedUser.getUsername(),
                ((Citizen)authenticatedUser).getFirstName(),
                ((Citizen)authenticatedUser).getLastName(),
                institution.getInstitutionName(),
                false,
                false

        );
        reportRepository.save(newReport);
        logger.info(String.format("A report has been posted by user %s", authenticatedUser.getUsername()));
        return newReport;
    }

    public int upvoteReport(ObjectId userId, ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        if (report != null) {
            // if the user already upvoted the post, remove upvote
            if (report.getUpvotes().contains(userId)) {
                report.getUpvotes().remove(userId);
            }

            // if the user has not upvoted the post, upvote
            else {
                report.getUpvotes().add(userId);
            }
            reportRepository.save(report);
            return report.getUpvotes().size();
        }
        else {
            return -1;
        }
    }

    public Comment commentToReport(ObjectId userId, ObjectId reportId, String text, String firstName, String lastName,String username) {
        Report report = reportRepository.findById(reportId);
        if (report != null) {
            Comment comment = new Comment(userId, text, firstName, lastName, username);
            report.getComments().add(comment);
            reportRepository.save(report);
            return comment;
        }
        else {
            return null;
        }
    }

    public Report deleteComment(ObjectId reportId, ObjectId commentId) {
        Report report = reportRepository.findById(reportId);
        if (report != null) {
            report.getComments().removeIf(comment -> comment.getId().toString().equals(commentId.toString()));
            reportRepository.save(report);
            return report;
        }
        else {
            return null;
        }
    }

    public Report getReport(ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        return report;
    }

    public List<Report> getAllReports(){
        List<Report> reports = reportRepository.findAll();
        Collections.reverse(reports);
        return reports;
    }

    public List<Report> getAllSolvedReports(){
        List<Report> reports = reportRepository.findAll();
        List<Report> solvedReports = new ArrayList<>();
        for(int i = 0; i < reports.size(); i++){
            if(reports.get(i).getSolution() != null){
                solvedReports.add(reports.get(i));
            }
        }
        Collections.reverse(solvedReports);
        return solvedReports;
    }

    public List<Report> getReportsByUser(ObjectId userId) { return reportRepository.findByUserId(userId); }
    public List<Report> getReportsByInstitution(ObjectId userId) { return reportRepository.findByInstitutionId(userId); }

    public Solution solveReport(ObjectId userId, ObjectId reportId, String description, MultipartFile solvedImage){

        Report report = reportRepository.findById(reportId);
        ApplicationUser user = (ApplicationUser) userRepository.findById(userId).get();
        if(user.getRole().toString().equals("CITIZEN") && report != null){
            report.setResolvedByCitizen(true);
            if(report.isResolvedByInstitution() == true){
                Solution solution = new Solution(description,solvedImage,true);
                report.setSolution(solution);
                reportRepository.save(report);
                return solution;
            }
            else{
                Solution solution = new Solution(description,solvedImage,false);
                report.setSolution(solution);
                reportRepository.save(report);
                return solution;
            }
        }
        else if(user.getRole().toString().equals("INSTITUTION") && report != null){
            report.setResolvedByInstitution(true);
            if(report.isResolvedByCitizen() == true){
                Solution solution = new Solution(description,solvedImage,true);
                report.setSolution(solution);
                reportRepository.save(report);
                return solution;
            }
            else{
                Solution solution = new Solution(description,solvedImage,false);
                report.setSolution(solution);
                reportRepository.save(report);
                return solution;
            }
        }
        else{
            return null;
        }
    }
    /*public String uploadReportImage(@AuthenticationPrincipal ApplicationUser authenticatedUser, MultipartFile file) {

        //1. Check if image is not empty
        //2. If file is an image
        //3. Whether the user exist in our database
        //4. ıf 3 grap some metadata from file if any
        //5. Store the image in s3 and update database with s3 image link (userProfileImageLink attribute)


        //grap metadata
        Map<String,String> metaData = new HashMap<>();
        metaData.put("Content-Type",file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));

        //check file is not empty
        if(!file.isEmpty()){
            //check image
            if(Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),
                    ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType())){
                //store image in s3 and update db userProfileımageLink with s3 link
                String path = String.format("%s/%s", BucketName.REPORT_IMAGE.getBucketName(),
                        authenticatedUser.getId());
                String fileName = String.format("%s-%s", file.getOriginalFilename()  , UUID.randomUUID() );
                try{
                    fileStore.save(path, fileName, Optional.of(metaData), file.getInputStream());
                    return fileName;
                    //user.setUserProfileImageLink(fileName);
                    //report.setReport_image_link(fileName);

                } catch(IOException e){
                    throw new IllegalStateException(e);
                }
            }
            //not an image
            else{
                throw new IllegalStateException("File type should be an image[" + file.getContentType() + "]");
            }
        }
        //empty file
        else{
            throw new IllegalStateException("File is not uploaded" );
        }

    }*/
}
