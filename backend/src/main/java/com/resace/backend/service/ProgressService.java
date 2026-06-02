package com.resace.backend.service;

import com.resace.backend.dto.ProgressSummaryDto;
import com.resace.backend.dto.ProgressSummaryDto.PaperStats;
import com.resace.backend.dto.ProgressSummaryDto.TopicStat;
import com.resace.backend.model.AppUser;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Topic;
import com.resace.backend.repository.QuizAttemptAnswerRepository;
import com.resace.backend.repository.QuizAttemptRepository;
import com.resace.backend.repository.TopicRepository;
import com.resace.backend.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final QuizAttemptRepository attemptRepository;
    private final QuizAttemptAnswerRepository attemptAnswerRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public ProgressSummaryDto getSummary(String email) {
        AppUser user = userRepository.findByEmail(email).orElseThrow();
        Long userId = user.getId();

        long totalAttempts = attemptRepository.countByUserId(userId);
        long totalAttempted = attemptRepository.sumTotalQuestionsByUserId(userId);
        long totalCorrect = attemptRepository.sumCorrectAnswersByUserId(userId);
        int overallAccuracy = totalAttempted > 0
            ? (int) Math.round((double) totalCorrect / totalAttempted * 100)
            : 0;

        PaperStats p1 = buildPaperStats(userId, Paper.PAPER_1);
        PaperStats p2 = buildPaperStats(userId, Paper.PAPER_2);

        List<TopicStat> topicStats = buildTopicStats(userId);

        return new ProgressSummaryDto(
            totalAttempts,
            totalAttempted,
            totalCorrect,
            overallAccuracy,
            p1,
            p2,
            topicStats
        );
    }

    private PaperStats buildPaperStats(Long userId, Paper paper) {
        long attempted = attemptRepository.sumTotalQuestionsByUserIdAndPaper(userId, paper);
        long correct = attemptRepository.sumCorrectAnswersByUserIdAndPaper(userId, paper);
        int accuracy = attempted > 0 ? (int) Math.round((double) correct / attempted * 100) : 0;
        return new PaperStats(attempted, correct, accuracy);
    }

    private List<TopicStat> buildTopicStats(Long userId) {
        List<Topic> topics = topicRepository.findAll();
        List<TopicStat> stats = new ArrayList<>();

        for (Topic topic : topics) {
            var answers = attemptAnswerRepository.findByUserIdAndTopicId(userId, topic.getId());
            if (answers.isEmpty()) continue;

            long attempted = answers.size();
            long correct = answers.stream().filter(answer -> answer.isCorrect()).count();
            int accuracy = attempted > 0 ? (int) Math.round((double) correct / attempted * 100) : 0;

            stats.add(new TopicStat(
                topic.getId(),
                topic.getName(),
                topic.getPaper().name(),
                attempted,
                correct,
                accuracy
            ));
        }

        return stats;
    }
}
