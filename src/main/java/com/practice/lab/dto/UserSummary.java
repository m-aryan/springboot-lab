package com.practice.lab.dto;

import com.practice.lab.model.AppUser;

public record UserSummary(
        Long id,
        String name
) {
    public static UserSummary from(AppUser user) {
        return new UserSummary(
                user.getId(),
                user.getName()
        );
    }
}
