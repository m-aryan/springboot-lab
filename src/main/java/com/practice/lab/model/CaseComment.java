package com.practice.lab.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@Table(name = "case_comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CaseComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id")
    private SupportCase supportCase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private AppUser author;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public CaseComment(SupportCase supportCase, AppUser author, String body) {
        this.supportCase = supportCase;
        this.author = author;
        this.body = body;
        this.createdAt = Instant.now();
    }
}
