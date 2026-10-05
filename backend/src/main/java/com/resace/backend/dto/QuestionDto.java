package com.resace.backend.dto;

import java.util.List;

public record QuestionDto(
    Long id,
    String contentKey,
    String paper,
    Long topicId,
    String topicName,
    String questionText,
    String difficulty,
    String plainEnglish,
    String memoryRule,
    String examShortcut,
    boolean premium,
    List<AnswerOptionDto> answerOptions
) {}
