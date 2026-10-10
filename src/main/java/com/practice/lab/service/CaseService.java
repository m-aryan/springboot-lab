package com.practice.lab.service;

import com.practice.lab.exception.CaseNotFoundException;
import com.practice.lab.model.CaseStatus;
import com.practice.lab.model.SupportCase;
import com.practice.lab.repository.CaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CaseService {
    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Transactional
    public SupportCase create(String title, String description) {
        return caseRepository.save(new SupportCase(title, description));
    }

    @Transactional(readOnly = true)
    public SupportCase getById(Long id) {
        return caseRepository.findById(id)
                .orElseThrow(() -> new CaseNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Page<SupportCase> list(CaseStatus status, Pageable pageable) {
        if (status == null) {
            return caseRepository.findAll(pageable);
        }
        return caseRepository.findByStatus(status, pageable);
    }

    @Transactional
    public SupportCase changeStatus(Long id, CaseStatus newStatus) {
        SupportCase supportCase = getById(id);
        supportCase.changeStatus(newStatus);
        return supportCase;
    }
}
