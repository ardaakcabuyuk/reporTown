package com.senior.reporTown.service;

import com.amazonaws.services.s3.AmazonS3;
import com.senior.reporTown.FileStore;
import com.senior.reporTown.buckets.BucketName;
import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Report;
import com.senior.reporTown.repository.ReportRepository;
import com.senior.reporTown.request.ReportRequest;
import org.bson.types.ObjectId;
import org.apache.http.entity.ContentType;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    //private final FileStore fileStore;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    public ReportService(ReportRepository reportRepository) { //,FileStore fileStore) {
        this.reportRepository = reportRepository;
        //this.fileStore = fileStore;
    }

    public Report postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, ReportRequest request) {
        //String link = uploadReportImage(authenticatedUser, request.getFile());
        Report newReport = new Report(
                request.getDescription(),
                request.getCategory(),
                request.getLocation(),
                request.getReport_image_link(),
                request.getFile(),
                authenticatedUser.getId()
        );
        reportRepository.save(newReport);
        logger.info(String.format("A report has been posted by user %s", authenticatedUser.getUsername()));
        return newReport;
    }

    public List<Report> getReportsByUser(ObjectId userId) {
        return reportRepository.findByUserId(userId);
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
