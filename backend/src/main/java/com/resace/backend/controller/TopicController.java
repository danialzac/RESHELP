package com.resace.backend.controller;

import com.resace.backend.dto.TopicDto;
import com.resace.backend.service.TopicService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public List<TopicDto> getTopics(@RequestParam(required = false) String paper) {
        return topicService.getTopics(paper);
    }
}
