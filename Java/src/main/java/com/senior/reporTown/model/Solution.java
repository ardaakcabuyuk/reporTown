package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Solution {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String description;
    private MultipartFile file; //image
    private boolean resolved;

    public Solution(String description, MultipartFile file, boolean resolved) {

        this.description = description;
        this.file = file;
        this.resolved = resolved;

    }
}
