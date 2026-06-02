package com.resace.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record SubmitAttemptRequest(
    @NotNull String paper,
    @NotNull String mode,
    Long topicId,
    @NotEmpty List<AnswerSubmission> answers
) {
    public record AnswerSubmission(
        @NotNull Long questionId,
        Long selectedOptionId
    ) {}
}
