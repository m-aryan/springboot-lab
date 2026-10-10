package com.practice.lab.repository;

import com.practice.lab.model.CaseStatus;
import com.practice.lab.model.SupportCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<SupportCase, Long> {

    // Custom method
    Page<SupportCase> findByStatus(CaseStatus caseStatus, Pageable pageable);
}
