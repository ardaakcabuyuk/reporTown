package com.senior.reporTown.controller;

import com.senior.reporTown.service.GoogleStorageClientService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CloudStorageController {
    @Autowired
    GoogleStorageClientService googleStorageClientService;

    @PostMapping(path = "report/{id}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean uploadReportImage(@RequestPart(value = "file", required = true) MultipartFile files, @PathVariable String id)  {
        try {
            return googleStorageClientService.upload(files, "report_images", id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping(path = "solution/{reportId}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean uploadSolutionImage(@RequestPart(value = "file", required = true) MultipartFile files, @PathVariable String reportId)  {
        try {
            return googleStorageClientService.upload(files, "solution_images", reportId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping(path = "user/{id}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean uploadProfilePicture(@RequestPart(value = "file", required = true) MultipartFile files, @PathVariable String id)  {
        try {
            return googleStorageClientService.upload(files, "profile_pictures", id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*@GetMapping(path = "/report/{id}/download")
    public ResponseEntity<Map<String, URL>> fileDownload(HttpServletRequest request,
                                                          @PathVariable ObjectId id,
                                                          HttpServletResponse response
    ) {
        try {
            URL url = googleStorageClientService.setSignedURL(id.toString());
            return ResponseEntity.ok()
                    .body(Map.of("url", url));
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No such file or directory");
        }
    }*/

    @GetMapping(path = "/refreshSignedURLs")
    public Boolean refreshSignedURLs(HttpServletRequest req, HttpServletResponse res) {
        googleStorageClientService.refreshSignedURLs();
        return true;
    }
}
