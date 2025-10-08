package com.oauth2.springwithmongodb.dtos;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password; // register only
}

