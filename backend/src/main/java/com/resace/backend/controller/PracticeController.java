package com.resace.backend.controller;

import com.resace.backend.dto.QuestionDto;
import com.resace.backend.service.PracticeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practice")
@RequiredArgsConstructor
public class PracticeController {

    private final PracticeService practiceService;

    @GetMapping("/topics/{topicId}/questions")
    public List<QuestionDto> getTopicQuestions(@PathVariable Long topicId) {
        return practiceService.getTopicQuestions(topicId);
    }

    @GetMapping("/random")
    public List<QuestionDto> getRandomQuestions(@RequestParam String paper) {
        return practiceService.getRandomQuestions(paper);
    }

    @GetMapping("/mock")
    public List<QuestionDto> getMockExamQuestions(@RequestParam String paper) {
        return practiceService.getMockExamQuestions(paper);
    }
}
