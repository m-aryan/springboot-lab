package com.practice.lab.repository;

import com.practice.lab.model.SupportCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<SupportCase, Long> {
}
