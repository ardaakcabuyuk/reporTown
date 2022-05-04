package com.senior.reporTown.request;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EditProfileRequest {

    private String bio;
    private String password;
}
