package com.senior.reporTown.cloud;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class GoogleHttpRequestInitializer implements HttpRequestInitializer {

    GoogleCredentials credentials;
    HttpCredentialsAdapter adapter;

    @Override
    public void initialize(HttpRequest httpRequest) throws IOException {
        try {
            credentials = GoogleCredentials
                    //.fromStream(new FileInputStream("Java/src/main/resources/reportown-google-api-key.json"))
                    .getApplicationDefault()
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
            adapter = new HttpCredentialsAdapter(credentials);

            adapter.initialize(httpRequest);
            httpRequest.setConnectTimeout(60000); // 1 minute connect timeout
            httpRequest.setReadTimeout(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GoogleCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(GoogleCredentials credentials) {
        this.credentials = credentials;
    }

    public HttpCredentialsAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(HttpCredentialsAdapter adapter) {
        this.adapter = adapter;
    }
}
