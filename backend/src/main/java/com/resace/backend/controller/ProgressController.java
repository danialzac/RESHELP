package com.resace.backend.controller;

import com.resace.backend.dto.ProgressSummaryDto;
import com.resace.backend.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping("/summary")
    public ProgressSummaryDto getSummary(Authentication authentication) {
        return progressService.getSummary(authentication.getName());
    }
}
