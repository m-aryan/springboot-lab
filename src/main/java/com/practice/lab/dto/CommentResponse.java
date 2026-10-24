package com.practice.lab.dto;

import com.practice.lab.model.CaseComment;

import java.time.Instant;

public record CommentResponse(
        Long id, UserSummary author, String body, Instant createdAt
) {
    public static CommentResponse from(CaseComment comment) {
        return new CommentResponse(
                comment.getId(),
                UserSummary.from(comment.getAuthor()),
                comment.getBody(),
                comment.getCreatedAt()
        );
    }
}