package com.practice.lab.model;

import com.practice.lab.exception.BusinessRuleException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "cases")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CaseStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private AppUser assignee;

    @OneToMany(mappedBy = "supportCase", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    private List<CaseComment> comments = new ArrayList<>();

    @Version
    private Long version;

    public SupportCase(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = CaseStatus.OPEN;
        this.createdAt = Instant.now();
    }

    public void assignTo(AppUser user) {
        ensureNotClosed();
        this.assignee = user;
        if (status == CaseStatus.OPEN) {
            status = CaseStatus.IN_PROGRESS;
        }
    }

    public void changeStatus(CaseStatus newStatus) {
        ensureNotClosed();
        if (newStatus == CaseStatus.CLOSED && assignee == null) {
            throw new BusinessRuleException("Case " + id + " cannot be closed without an assignee");
        }
        this.status = newStatus;
    }

    public CaseComment addComment(AppUser author, String body) {
        ensureNotClosed();
        CaseComment comment = new CaseComment(this, author, body);
        comments.add(comment);
        return comment;
    }

    public List<CaseComment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    private void ensureNotClosed() {
        if (status == CaseStatus.CLOSED) {
            throw new BusinessRuleException("Case " + id + " is closed");
        }
    }
}