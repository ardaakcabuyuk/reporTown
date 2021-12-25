package com.senior.reporTown.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.senior.reporTown.model.Report;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class ProfileResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("user")
    private UserDetails user;

    @JsonProperty("reports")
    private List<Report> reports;
}
