package com.oauth2.springwithmongodb.dtos;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
