package com.resace.backend.controller;

import com.resace.backend.dto.AttemptResultResponse;
import com.resace.backend.dto.SubmitAttemptRequest;
import com.resace.backend.service.AttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final AttemptService attemptService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttemptResultResponse submitAttempt(
        Authentication authentication,
        @Valid @RequestBody SubmitAttemptRequest request
    ) {
        return attemptService.submit(authentication.getName(), request);
    }
}
