package com.senior.reporTown.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bson.json.JsonObject;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReportRequest {

    private final String description;
    private final String category;
    private final String file; //url for now
    private final JsonObject location;

}
