package com.resace.backend.dto;

import java.util.List;

public record QuestionDto(
    Long id,
    String paper,
    Long topicId,
    String topicName,
    String questionText,
    String difficulty,
    List<AnswerOptionDto> answerOptions
) {}
