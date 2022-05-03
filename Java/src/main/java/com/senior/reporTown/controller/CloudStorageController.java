package com.senior.reporTown.controller;

import com.google.api.services.storage.model.StorageObject;
import com.google.common.io.Files;
import com.senior.reporTown.cloud.GoogleStorageClientAdapter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CloudStorageController {
    @Autowired
    GoogleStorageClientAdapter googleStorageClientAdapter;

    @PostMapping(path = "report/{id}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean uploadFile(@RequestPart(value = "file", required = true) MultipartFile files, @PathVariable String id)  {
        try {
            return googleStorageClientAdapter.upload(files, id, "prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*@GetMapping(path = "/report/{id}/download")
    public String fileDownload(HttpServletRequest request,
                                                          @PathVariable ObjectId id,
                                                          HttpServletResponse response
    ) {
        try {
            googleStorageClientAdapter.download(id.toString());

            return "asdfasfas";
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No such file or directory");
        }
    }*/
}
