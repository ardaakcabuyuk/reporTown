package com.senior.reporTown.cloud;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.StorageObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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

    public StorageObject download(String fileName) throws IOException {
        StorageObject object = storage.objects().get(bucketName, fileName).execute();
        File file = new File("./" + fileName);
        FileOutputStream os = new FileOutputStream(file);

        storage.getRequestFactory()
                .buildGetRequest(new GenericUrl(object.getMediaLink()))
                .execute()
                .download(os);
        object.set("file", file);
        return object;
    }
}
