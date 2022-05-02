package com.senior.reporTown.cloud;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.storage.Storage;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Configuration
public class GoogleStorageCloudConfig {

    @Bean
    Storage configStorageClient() throws GeneralSecurityException, IOException {

        Storage storage = new Storage(GoogleNetHttpTransport.newTrustedTransport(),
                new GsonFactory(), new GoogleHttpRequestInitializer());
        return storage;
    }

}
