package com.practice.lab.dto;

import com.practice.lab.model.CaseStatus;

public record ChangeStatusRequest(CaseStatus status) {
}
