package com.senior.reporTown.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReportRequest {

    private ObjectId institutionId;
    private String description;
    private String category;
    private ArrayList<ObjectId> comments;
    private ArrayList<ObjectId> upvotes;
    private JsonObject location;
    private String report_image_link;
    private MultipartFile file;

}
