package com.practice.lab.dto;

import jakarta.validation.constraints.NotNull;

public record AssignCaseRequest(@NotNull Long userId) {
}
