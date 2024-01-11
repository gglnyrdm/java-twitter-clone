package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record CommentsDto(long id, String content, long userId, long tweetId, LocalDateTime createdAt) {
}
