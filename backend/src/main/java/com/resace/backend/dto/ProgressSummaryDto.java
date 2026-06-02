package com.resace.backend.dto;

import java.util.List;

public record ProgressSummaryDto(
    long totalAttempts,
    long totalQuestionsAttempted,
    long totalCorrect,
    int overallAccuracy,
    PaperStats paper1Stats,
    PaperStats paper2Stats,
    List<TopicStat> topicStats
) {
    public record PaperStats(
        long questionsAttempted,
        long correct,
        int accuracy
    ) {}

    public record TopicStat(
        Long topicId,
        String topicName,
        String paper,
        long attempted,
        long correct,
        int accuracy
    ) {}
}
