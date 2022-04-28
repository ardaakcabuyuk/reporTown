package com.senior.reporTown.request;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SolutionRequest {

    //private ObjectId institutionId;
    private String description;
    private MultipartFile file;
}
