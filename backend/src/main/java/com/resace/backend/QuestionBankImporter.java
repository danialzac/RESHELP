package com.resace.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resace.backend.content.QuestionBankPayload;
import com.resace.backend.content.QuestionBankPayload.AnswerOptionContent;
import com.resace.backend.content.QuestionBankPayload.QuestionContent;
import com.resace.backend.content.QuestionBankPayload.TopicContent;
import com.resace.backend.model.AnswerOption;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Question;
import com.resace.backend.model.Topic;
import com.resace.backend.repository.QuestionRepository;
import com.resace.backend.repository.TopicRepository;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public class QuestionBankImporter implements CommandLineRunner {

    private static final String QUESTION_BANK_RESOURCE = "content/question-bank.json";

    private final ObjectMapper objectMapper;
    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource(QUESTION_BANK_RESOURCE);
        if (!resource.exists()) {
            log.info("No structured question bank found at {} — skipping import.", QUESTION_BANK_RESOURCE);
            return;
        }

        try (InputStream inputStream = resource.getInputStream()) {
            QuestionBankPayload payload = objectMapper.readValue(inputStream, QuestionBankPayload.class);
            if (payload.questions() == null || payload.questions().isEmpty()) {
                log.info("Structured question bank is empty — nothing to import.");
                return;
            }

            int importedCount = 0;
            for (QuestionContent content : payload.questions()) {
                if (content.contentKey() == null || content.contentKey().isBlank()) {
                    log.warn("Skipping question with missing contentKey: {}", content.questionText());
                    continue;
                }
                importQuestion(content);
                importedCount++;
            }
            log.info("Structured question bank import completed: {} question(s) processed.", importedCount);
        }
    }

    private void importQuestion(QuestionContent content) {
        Topic topic = resolveTopic(content.paper(), content.topic());
        Question question = questionRepository.findByContentKey(content.contentKey())
            .orElseGet(Question::new);

        question.setContentKey(content.contentKey());
        question.setPaper(Paper.valueOf(content.paper()));
        question.setTopic(topic);
        question.setSourceReference(content.sourceReference());
        question.setSubtopic(content.subtopic());
        question.setPrincipleTested(content.principleTested());
        question.setQuestionText(content.questionText());
        question.setDifficulty(content.difficulty());
        question.setExamFrequency(content.examFrequency());
        question.setExplanation(content.explanation());
        question.setPlainEnglish(content.plainEnglish());
        question.setCavemanVersion(content.cavemanVersion());
        question.setMinimalVersion(content.minimalVersion());
        question.setMemoryRule(content.memoryRule());
        question.setExamShortcut(content.examShortcut());
        question.setExamTrap(content.examTrap());
        question.setInteractiveFormat(content.interactiveFormat());
        question.setPremium(Boolean.TRUE.equals(content.premium()));
        question.setActive(content.active() == null || content.active());

        List<AnswerOption> answerOptions = new ArrayList<>();
        if (content.answerOptions() != null) {
            for (AnswerOptionContent optionContent : content.answerOptions()) {
                answerOptions.add(AnswerOption.builder()
                    .question(question)
                    .optionLabel(optionContent.optionLabel())
                    .optionText(optionContent.optionText())
                    .correct(optionContent.correct())
                    .build());
            }
        }
        question.setAnswerOptions(answerOptions);
        questionRepository.save(question);
    }

    private Topic resolveTopic(String paperValue, TopicContent topicContent) {
        Paper paper = Paper.valueOf(paperValue);
        return topicRepository.findBySlug(topicContent.slug())
            .map(existing -> {
                existing.setName(topicContent.name());
                existing.setPaper(paper);
                existing.setDescription(topicContent.description());
                return topicRepository.save(existing);
            })
            .orElseGet(() -> topicRepository.save(Topic.builder()
                .slug(topicContent.slug())
                .name(topicContent.name())
                .paper(paper)
                .description(topicContent.description())
                .build()));
    }
}
