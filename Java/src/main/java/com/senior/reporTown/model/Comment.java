package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Comment {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    private String text;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    public Comment(ObjectId userId, String text) {
        id = new ObjectId();
        this.userId = userId;
        this.text = text;
        this.date = new Date();
    }
}
