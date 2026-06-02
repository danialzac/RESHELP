package com.resace.backend.dto;

import java.util.List;

public record AttemptResultResponse(
    Long id,
    String paper,
    String mode,
    int score,
    int totalQuestions,
    int correctAnswers,
    List<ReviewedAnswer> reviewedAnswers
) {
    public record ReviewedAnswer(
        Long questionId,
        String questionText,
        Long selectedOptionId,
        Long correctOptionId,
        boolean correct,
        String explanation,
        String plainEnglish,
        String memoryRule,
        String examTrap,
        List<ReviewedOption> answerOptions
    ) {}

    public record ReviewedOption(
        Long id,
        String optionLabel,
        String optionText,
        boolean correct
    ) {}
}
