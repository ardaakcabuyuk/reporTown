package com.senior.reporTown.service;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.services.storage.Storage;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.google.api.services.storage.model.StorageObject;
import com.senior.reporTown.model.Report;
import com.senior.reporTown.repository.ReportRepository;
import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleStorageClientService {
    private final String BUCKET_NAME = "reportown-347509.appspot.com";
    private final String PROJECT_ID = "reportown-347509";
    private final Logger logger = LoggerFactory.getLogger(GoogleStorageClientService.class);
    Storage storage;
    ReportRepository reportRepository;

    @Autowired
    public GoogleStorageClientService(Storage storage,
                                      ReportRepository reportRepository) {
        this.storage = storage;
        this.reportRepository = reportRepository;
    }

    public Boolean upload(MultipartFile file, String reportId, String prefixName) throws IOException {
        StorageObject object = new StorageObject();
        object.setName("report_images/" + reportId + "/" + reportId);
        InputStream targetStream = new ByteArrayInputStream(file.getBytes());
        storage.objects().insert(BUCKET_NAME, object, new AbstractInputStreamContent(file.getOriginalFilename()) {
            @Override
            public long getLength() throws IOException {
                return file.getSize();
            }

            @Override
            public boolean retrySupported() {
                return false;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return targetStream;
            }
        }).execute();

        return true;
    }

    public URL setSignedURL(String id) {
        //Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/ardaakcabuyuk/IdeaProjects/reporTown/reportown-google-api-key.json"));
        try {
            com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.getApplicationDefault()).setProjectId(PROJECT_ID).build().getService();
            String fileName = "report_images/" + id + "/" + id;
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(BUCKET_NAME, fileName)).build();
            URL url = storage.signUrl(blobInfo, 24, TimeUnit.HOURS, com.google.cloud.storage.Storage.SignUrlOption.withV4Signature());
            Report report = reportRepository.findById(new ObjectId(id));
            report.setImage(url.toString());
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void refreshSignedURLs() {
        //Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/ardaakcabuyuk/IdeaProjects/reporTown/reportown-google-api-key.json"));
        try {
            com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).setCredentials(GoogleCredentials.getApplicationDefault()).build().getService();

            List<Report> reports = reportRepository.findAll();
            reports.forEach(r -> {
                String fileName = "report_images/" + r.getId() + "/" + r.getId();
                BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(BUCKET_NAME, fileName)).build();
                URL url = storage.signUrl(blobInfo, 24, TimeUnit.HOURS, com.google.cloud.storage.Storage.SignUrlOption.withV4Signature());
                r.setImage(url.toString());
                reportRepository.save(r);
                logger.info("Image URL is refreshed for report " + r.getId().toString());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
