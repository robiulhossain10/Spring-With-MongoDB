package com.oauth2.springwithmongodb.dtos;

import lombok.Builder;

@Builder
public record LoginDTO(
        String email,
        String password) {
}
