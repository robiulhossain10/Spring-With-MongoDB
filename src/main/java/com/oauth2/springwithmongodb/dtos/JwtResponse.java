package com.oauth2.springwithmongodb.dtos;

import com.oauth2.springwithmongodb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private User user;
}

