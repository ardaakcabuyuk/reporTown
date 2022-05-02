package com.senior.reporTown.cloud;

import com.google.api.services.storage.model.StorageObject;
import com.google.common.io.Files;
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


    @PostMapping(path = "report/{reportId}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean uploadFile(@RequestPart(value = "file", required = true) MultipartFile files, @PathVariable String reportId)  {
        try {
            return googleStorageClientAdapter.upload(files, reportId, "prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(name = "file-download", path = "download",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> fileDownload(HttpServletRequest request,
                                                          @RequestParam(value = "file", required = false) String path,
                                                          HttpServletResponse response
    ) {
        try {
            StorageObject object = googleStorageClientAdapter.download(path);


            byte[] res = Files.toByteArray((File) object.get("file"));
            ByteArrayResource resource = new ByteArrayResource(res);

            return ResponseEntity.ok()
                    .contentLength(res.length)
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + path + "\"").body(resource);
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No such file or directory");
        }
    }
}
