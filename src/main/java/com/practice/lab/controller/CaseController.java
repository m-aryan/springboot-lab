package com.practice.lab.controller;

import com.practice.lab.dto.CaseResponse;
import com.practice.lab.dto.CreateCaseRequest;
import com.practice.lab.service.CaseService;
import org.jspecify.annotations.NonNull;
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
}
