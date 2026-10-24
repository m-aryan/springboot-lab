package com.practice.lab.dto;

import com.practice.lab.model.CaseStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeStatusRequest(@NotNull CaseStatus status) {
}
