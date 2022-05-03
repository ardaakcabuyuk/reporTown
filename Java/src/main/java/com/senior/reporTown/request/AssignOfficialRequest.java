package com.senior.reporTown.request;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AssignOfficialRequest {
    private ObjectId reportId;
}
