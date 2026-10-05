package com.resace.backend.service;

import com.resace.backend.dto.AttemptResultResponse;
import com.resace.backend.dto.AttemptResultResponse.ReviewedAnswer;
import com.resace.backend.dto.AttemptResultResponse.ReviewedOption;
import com.resace.backend.dto.SubmitAttemptRequest;
import com.resace.backend.model.AnswerOption;
import com.resace.backend.model.AppUser;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Question;
import com.resace.backend.model.QuizAttempt;
import com.resace.backend.model.QuizAttemptAnswer;
import com.resace.backend.model.QuizMode;
import com.resace.backend.model.Topic;
import com.resace.backend.repository.AnswerOptionRepository;
import com.resace.backend.repository.QuestionRepository;
import com.resace.backend.repository.QuizAttemptAnswerRepository;
import com.resace.backend.repository.QuizAttemptRepository;
import com.resace.backend.repository.TopicRepository;
import com.resace.backend.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final QuizAttemptRepository attemptRepository;
    private final QuizAttemptAnswerRepository attemptAnswerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Transactional
    public AttemptResultResponse submit(String email, SubmitAttemptRequest request) {
        AppUser user = userRepository.findByEmail(email).orElseThrow();
        Paper paper = Paper.valueOf(request.paper());
        QuizMode mode = QuizMode.valueOf(request.mode());
        Topic topic = request.topicId() != null
            ? topicRepository.findById(request.topicId()).orElse(null)
            : null;

        int correctCount = 0;
        List<ReviewedAnswer> reviewed = new ArrayList<>();

        for (SubmitAttemptRequest.AnswerSubmission submission : request.answers()) {
            Question question = questionRepository.findById(submission.questionId()).orElseThrow();
            AnswerOption selectedOption = submission.selectedOptionId() != null
                ? answerOptionRepository.findById(submission.selectedOptionId()).orElse(null)
                : null;

            AnswerOption correctOption = question.getAnswerOptions().stream()
                .filter(AnswerOption::isCorrect)
                .findFirst()
                .orElse(null);

            boolean isCorrect = selectedOption != null && selectedOption.isCorrect();
            if (isCorrect) correctCount++;

            List<ReviewedOption> reviewedOptions = question.getAnswerOptions().stream()
                .map(o -> new ReviewedOption(o.getId(), o.getOptionLabel(), o.getOptionText(), o.isCorrect()))
                .toList();

            reviewed.add(new ReviewedAnswer(
                question.getId(),
                question.getContentKey(),
                question.getQuestionText(),
                selectedOption != null ? selectedOption.getId() : null,
                correctOption != null ? correctOption.getId() : null,
                isCorrect,
                question.getExplanation(),
                question.getPlainEnglish(),
                question.getCavemanVersion(),
                question.getMinimalVersion(),
                question.getMemoryRule(),
                question.getExamShortcut(),
                question.getExamTrap(),
                question.getPrincipleTested(),
                question.getInteractiveFormat(),
                question.isPremium(),
                reviewedOptions
            ));
        }

        int total = request.answers().size();
        int score = total > 0 ? (int) Math.round((double) correctCount / total * 100) : 0;

        QuizAttempt attempt = QuizAttempt.builder()
            .user(user)
            .paper(paper)
            .mode(mode)
            .topic(topic)
            .score(score)
            .totalQuestions(total)
            .correctAnswers(correctCount)
            .startedAt(LocalDateTime.now())
            .completedAt(LocalDateTime.now())
            .build();
        attempt = attemptRepository.save(attempt);

        for (int i = 0; i < request.answers().size(); i++) {
            SubmitAttemptRequest.AnswerSubmission submission = request.answers().get(i);
            Question question = questionRepository.findById(submission.questionId()).orElseThrow();
            AnswerOption selectedOption = submission.selectedOptionId() != null
                ? answerOptionRepository.findById(submission.selectedOptionId()).orElse(null)
                : null;
            boolean isCorrect = selectedOption != null && selectedOption.isCorrect();

            attemptAnswerRepository.save(QuizAttemptAnswer.builder()
                .attempt(attempt)
                .question(question)
                .selectedOption(selectedOption)
                .correct(isCorrect)
                .build());
        }

        return new AttemptResultResponse(
            attempt.getId(),
            paper.name(),
            mode.name(),
            score,
            total,
            correctCount,
            reviewed
        );
    }
}
