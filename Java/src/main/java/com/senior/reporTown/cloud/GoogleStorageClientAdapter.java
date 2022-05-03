package com.senior.reporTown.cloud;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.services.storage.Storage;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.StorageOptions;
import com.google.api.services.storage.model.StorageObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;

@Component
public class GoogleStorageClientAdapter {
    Storage storage;
    String bucketName;

    public GoogleStorageClientAdapter(@Autowired Storage storage, @Value("reportown-347509.appspot.com") String bucketName) {
        this.storage = storage;
        this.bucketName = bucketName;
    }

    public Boolean upload(MultipartFile file, String reportId, String prefixName) throws IOException {
        StorageObject object = new StorageObject();
        object.setName("report_images/" + reportId + "/" + reportId);
        InputStream targetStream = new ByteArrayInputStream(file.getBytes());
        storage.objects().insert(bucketName, object, new AbstractInputStreamContent(file.getOriginalFilename()) {
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

    /*public void download(String id) throws IOException {
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/ardaakcabuyuk/IdeaProjects/reporTown/reportown-google-api-key.json"));
        com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("reportown-347509").build().getService();
        String fileName = "report_images/" + id + "/" + id;
        Blob blob = storage.get(BlobId.of(bucketName, fileName));
        blob.downloadTo(Paths.get(id));

        System.out.println(
                "Downloaded object "
                        + fileName
                        + " from bucket name "
                        + bucketName
                        + " to "
                        + fileName);

        StorageObject object = storage.objects().get(bucketName, fileName).execute();
        File file = new File("a");
        FileOutputStream os = new FileOutputStream(file);

        storage.getRequestFactory()
                .buildGetRequest(new GenericUrl(object.getMediaLink()))
                .execute()
                .download(os);
        object.set("file", file);
        return object;
    }*/
}
