package com.resace.backend.content;

import java.util.List;

public record QuestionBankPayload(
    String version,
    String generatedAt,
    List<QuestionContent> questions
) {
    public record QuestionContent(
        String contentKey,
        String paper,
        TopicContent topic,
        String sourceReference,
        String subtopic,
        String principleTested,
        String difficulty,
        String examFrequency,
        String questionText,
        String explanation,
        String plainEnglish,
        String cavemanVersion,
        String minimalVersion,
        String memoryRule,
        String examShortcut,
        String examTrap,
        String interactiveFormat,
        Boolean premium,
        Boolean active,
        List<AnswerOptionContent> answerOptions
    ) {}

    public record TopicContent(
        String slug,
        String name,
        String description
    ) {}

    public record AnswerOptionContent(
        String optionLabel,
        String optionText,
        boolean correct
    ) {}
}
