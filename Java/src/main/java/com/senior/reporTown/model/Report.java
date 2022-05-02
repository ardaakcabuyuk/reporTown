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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    //@JsonSerialize(using = ToStringSerializer.class)
    //private ObjectId solutionId;


    private String description;
    private String category;
    private ArrayList<Comment> comments;
    @JsonSerialize(contentUsing = ToStringSerializer.class)
    private ArrayList<ObjectId> upvotes;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private Solution solution;

    private String longitude;
    private String latitude;

    private String username;
    private String firstName;
    private String lastName;

    private String institutionName;
    private boolean resolvedByCitizen;
    private boolean resolvedByInstitution;

    public Report(String description,
                  String category,
                  ObjectId userId,
                  ObjectId institutionId,
                  String longitude,
                  String latitude,
                  String username,
                  String firstName,
                  String lastName,
                  String institutionName) {
        this.description = description;
        this.category = category;
        comments = new ArrayList<>();
        upvotes = new ArrayList<>();
        this.userId = userId;
        this.institutionId = institutionId;
        date = new Date();
        this.solution = null;
        this.longitude = longitude;
        this.latitude = latitude;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institutionName = institutionName;
        this.resolvedByCitizen = false;
        this.resolvedByInstitution = false;
    }
}

