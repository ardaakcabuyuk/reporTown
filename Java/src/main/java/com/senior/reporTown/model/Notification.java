package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@Document(collection = "notifications")
public class Notification {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    private ObjectId reportId;
    private ObjectId destUserId;
    private String actionTaker;
    private NotificationType type;
    private String description;

    public Notification(String actionTaker, ObjectId destUserId, ObjectId reportId, NotificationType type, String description) {
        this.actionTaker = actionTaker;
        this.destUserId = destUserId;
        this.reportId = reportId;
        this.type = type;
        this.description = description;
    }
}
