package com.practice.lab.dto;

import com.practice.lab.model.CaseStatus;
import com.practice.lab.model.SupportCase;

import java.time.Instant;

public record CaseResponse(
        Long id,
        String title,
        String description,
        CaseStatus status,
        Instant createdAt
) {
    public static CaseResponse from(SupportCase supportCase) {
        return new CaseResponse(
                supportCase.getId(),
                supportCase.getTitle(),
                supportCase.getDescription(),
                supportCase.getStatus(),
                supportCase.getCreatedAt()
        );
    }
}
