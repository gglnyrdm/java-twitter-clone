package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record TweetsDto(long id, String content, long userId, LocalDateTime createdAt) {
}
