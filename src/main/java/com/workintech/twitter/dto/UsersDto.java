package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record UsersDto(long id, String username, String email, String firstName,
                       String lastName, LocalDateTime created_at) {
}
