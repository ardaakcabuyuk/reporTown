package com.senior.reporTown.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "reports")
public class Report {


    @Id
    private ObjectId _id;
    private String description;
    private String category;


    public Report(String description, String category) {
        this.description = description;
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }


}

