package com.practice.lab.controller;

import com.practice.lab.dto.CaseResponse;
import com.practice.lab.dto.ChangeStatusRequest;
import com.practice.lab.dto.CreateCaseRequest;
import com.practice.lab.model.CaseStatus;
import com.practice.lab.service.CaseService;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping("/create-case")
    public CaseResponse createCase(@RequestBody @NonNull CreateCaseRequest request) {
        return CaseResponse.from(
                caseService.create(
                        request.title(),
                        request.description()
                )
        );
    }

    @GetMapping("/{id}")
    public CaseResponse getById(@PathVariable Long id) {
        return CaseResponse.from(
                caseService.getById(id)
        );
    }

    @GetMapping("/all")
    public PagedModel<CaseResponse> list(@RequestBody(required = false) CaseStatus status, Pageable pageable) {
        return new PagedModel<>(
                caseService.list(status, pageable)
                        .map(CaseResponse::from));
    }

    @PatchMapping("/{id}/status")
    public CaseResponse changeStatus(@PathVariable Long id, @RequestBody ChangeStatusRequest request) {
        return CaseResponse.from(caseService.changeStatus(id, request.status()));
    }
}
