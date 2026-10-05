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
        String contentKey,
        String questionText,
        Long selectedOptionId,
        Long correctOptionId,
        boolean correct,
        String explanation,
        String plainEnglish,
        String cavemanVersion,
        String minimalVersion,
        String memoryRule,
        String examShortcut,
        String examTrap,
        String principleTested,
        String interactiveFormat,
        boolean premium,
        List<ReviewedOption> answerOptions
    ) {}

    public record ReviewedOption(
        Long id,
        String optionLabel,
        String optionText,
        boolean correct
    ) {}
}
