package com.resace.backend.service;

import com.resace.backend.dto.AnswerOptionDto;
import com.resace.backend.dto.QuestionDto;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Question;
import com.resace.backend.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeService {

    private static final int RANDOM_PRACTICE_SIZE = 10;
    private static final int MOCK_EXAM_SIZE = 30;

    private final QuestionRepository questionRepository;

    public List<QuestionDto> getTopicQuestions(Long topicId) {
        return questionRepository.findByTopicIdAndActiveTrue(topicId)
            .stream().map(this::toDto).toList();
    }

    public List<QuestionDto> getRandomQuestions(String paper) {
        return questionRepository.findRandomByPaper(paper, RANDOM_PRACTICE_SIZE)
            .stream().map(this::toDto).toList();
    }

    public List<QuestionDto> getMockExamQuestions(String paper) {
        return questionRepository.findRandomByPaper(paper, MOCK_EXAM_SIZE)
            .stream().map(this::toDto).toList();
    }

    private QuestionDto toDto(Question q) {
        List<AnswerOptionDto> options = q.getAnswerOptions().stream()
            .map(o -> new AnswerOptionDto(o.getId(), o.getOptionLabel(), o.getOptionText()))
            .toList();
        return new QuestionDto(
            q.getId(),
            q.getContentKey(),
            q.getPaper().name(),
            q.getTopic().getId(),
            q.getTopic().getName(),
            q.getQuestionText(),
            q.getDifficulty(),
            q.getPlainEnglish(),
            q.getMemoryRule(),
            q.getExamShortcut(),
            q.isPremium(),
            options
        );
    }
}
