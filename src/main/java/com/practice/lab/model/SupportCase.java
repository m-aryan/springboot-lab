package com.practice.lab.model;

import com.practice.lab.exception.InvalidCaseStateException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Entity
@Table(name = "cases")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CaseStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public SupportCase(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = CaseStatus.OPEN;
        this.createdAt = Instant.now();
    }

    public void changeStatus(CaseStatus newStatus) {
        if (this.status == CaseStatus.CLOSED) {
            throw new InvalidCaseStateException("Cannot change status of a closed Case");
        }
        this.status = newStatus;
    }
}