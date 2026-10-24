package com.practice.lab.repository;

import com.practice.lab.model.CaseStatus;
import com.practice.lab.model.SupportCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CaseRepository extends JpaRepository<SupportCase, Long> {

    // Custom method
    Page<SupportCase> findByStatus(CaseStatus caseStatus, Pageable pageable);

    @Query("""
            SELECT c FROM SupportCase c
            LEFT JOIN FETCH c.assignee
            LEFT JOIN FETCH c.comments cm
            LEFT JOIN FETCH cm.author
            WHERE c.id = :id
            ORDER BY cm.createdAt
            """)
    Optional<SupportCase> findDetailById(@Param("id") Long id);
}
