package com.senior.reporTown.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.util.ArrayList;

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
    private File[] images;



    public Report(String description, String category, ArrayList<ObjectId> comments, ArrayList<ObjectId> upvotes, JsonObject location, File[] images,ObjectId _user_id) {
        this.description = description;
        this.category = category;
        this.comments = comments;
        this.upvotes = upvotes;
        this.location = location;
        this.images = images;
        this._user_id = _user_id;
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

    public File[] getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }


}

