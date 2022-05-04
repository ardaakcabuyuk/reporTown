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
import com.senior.reporTown.model.*;
import com.senior.reporTown.repository.ReportRepository;
import com.senior.reporTown.repository.UserRepository;
import com.senior.reporTown.security.UserRole;
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
    UserRepository userRepository;

    @Autowired
    public GoogleStorageClientService(Storage storage,
                                      ReportRepository reportRepository,
                                      UserRepository userRepository) {
        this.storage = storage;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public Boolean upload(MultipartFile file, String dir, String id) throws IOException {
        StorageObject object = new StorageObject();
        object.setName(dir + "/" + id + "/" + id);
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
        setSignedURL(dir, id);
        return true;
    }

    public void setSignedURL(String dir, String id) {
        try {
            //Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/ardaakcabuyuk/IdeaProjects/reporTown/reportown-google-api-key.json"));
            com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.getApplicationDefault()).setProjectId(PROJECT_ID).build().getService();
            String fileName = dir + "/" + id + "/" + id;
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(BUCKET_NAME, fileName)).build();
            URL url = storage.signUrl(blobInfo, 24, TimeUnit.HOURS, com.google.cloud.storage.Storage.SignUrlOption.withV4Signature());
            if (dir.equals("report_images")) {
                Report report = reportRepository.findById(new ObjectId(id));
                report.setImage(url.toString());
                reportRepository.save(report);
            }
            else if (dir.equals("solution_images")) {
                Report report = reportRepository.findById(new ObjectId(id));
                report.getSolution().setImage(url.toString());
                reportRepository.save(report);
            }
            else if (dir.equals("profile_pictures")) {
                ApplicationUser user = userRepository.findById(new ObjectId(id)).get();
                if (user.getRole() == UserRole.CITIZEN) {
                    ((Citizen) user).setProfilePicture(url.toString());
                    List<Report> userReports = reportRepository.findByUserId(((Citizen) user).getId());
                    userReports.forEach(r -> {
                        r.setProfilePicture(url.toString());
                        reportRepository.save(r);
                    });
                } else if (user.getRole() == UserRole.INSTITUTION) {
                    ((Institution) user).setProfilePicture(url.toString());
                } else if (user.getRole() == UserRole.OFFICIAL) {
                    ((Official) user).setProfilePicture(url.toString());
                }
                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshSignedURLs() {
        List<Report> reports = reportRepository.findAll();
        reports.forEach(r -> {
            setSignedURL("report_images", r.getId().toString());
            if (r.getSolution() != null) {
                setSignedURL("solution_images", r.getId().toString());
            }
        });

        List<ApplicationUser> users = userRepository.findAll();
        users.forEach(u -> setSignedURL("profile_pictures", u.getId().toString()));
    }
}
