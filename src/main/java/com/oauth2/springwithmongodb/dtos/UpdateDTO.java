package com.oauth2.springwithmongodb.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateDTO {

    private String id;
    private String name;
    private String email;
    private String password;
}
