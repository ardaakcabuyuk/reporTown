package com.senior.reporTown.model;

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
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "reports")
public class Report {


    @Id
    private ObjectId _id;
    private ObjectId _user_id;
    private ObjectId _solution_id;

    private String description;
    private String category;
    private ArrayList<ObjectId> comments;
    private ArrayList<ObjectId> upvotes;
    private JsonObject location;
    private String report_image_link;
    private MultipartFile file;



    public Report(String description, String category, ArrayList<ObjectId> comments, ArrayList<ObjectId> upvotes, JsonObject location, String report_image_link, MultipartFile file, ObjectId id) {
        this.description = description;
        this.category = category;
        this.comments = comments;
        this.upvotes = upvotes;
        this.location = location;
        this.report_image_link = report_image_link;
        this.file = file;
    }

    public ObjectId get_id() {
        return _id;
    }

    public ArrayList<ObjectId> getComments() {
        return comments;
    }

    public ArrayList<ObjectId> getUpvotes() {
        return upvotes;
    }

    public JsonObject getLocation() {
        return location;
    }

    public Optional<String> getReportImageLink() {
        return Optional.ofNullable(report_image_link);
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setComments(ArrayList<ObjectId> comments) {
        this.comments = comments;
    }

    public void setUpvotes(ArrayList<ObjectId> upvotes) {
        this.upvotes = upvotes;
    }

    public void setLocation(JsonObject location) {
        this.location = location;
    }

    public void setReport_image_link(String report_image_link) {
        this.report_image_link = report_image_link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return description.equals(report.description) && category.equals(report.category) && comments.equals(report.comments) && upvotes.equals(report.upvotes) && location.equals(report.location) && report_image_link.equals(report.report_image_link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, category, comments, upvotes, location, report_image_link);
    }
}

