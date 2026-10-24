package com.practice.lab.dto;

import com.practice.lab.model.CaseStatus;
import com.practice.lab.model.SupportCase;

import java.time.Instant;
import java.util.List;

public record CaseDetailResponse(
        Long id,
        String title,
        String description,
        CaseStatus status,
        Instant createdAt,
        UserSummary assignee,
        List<CommentResponse> comments
) {

    public static CaseDetailResponse from(SupportCase supportCase) {
        return new CaseDetailResponse(
                supportCase.getId(),
                supportCase.getTitle(),
                supportCase.getDescription(),
                supportCase.getStatus(),
                supportCase.getCreatedAt(),
                supportCase.getAssignee() == null ? null : UserSummary.from(supportCase.getAssignee()),
                supportCase.getComments().stream().map(CommentResponse::from).toList()
        );
    }

}