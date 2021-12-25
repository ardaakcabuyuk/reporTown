package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "reports")
public class Report {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId institutionId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId solutionId;

    private String description;
    private String category;
    private ArrayList<ObjectId> comments;
    private ArrayList<ObjectId> upvotes;
    private JsonObject location;
    private String report_image_link;
    private MultipartFile file;

    public Report(String description,
                  String category,
                  JsonObject location,
                  String report_image_link,
                  MultipartFile file,
                  ObjectId userId,
                  ObjectId institutionId) {
        this.description = description;
        this.category = category;
        comments = null;
        upvotes = null;
        this.location = location;
        this.userId = userId;
        this.institutionId = institutionId;
        solutionId = null;
        this.report_image_link = report_image_link;
        this.file = file;
    }
}

