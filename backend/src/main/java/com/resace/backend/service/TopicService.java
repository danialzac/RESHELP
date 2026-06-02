package com.resace.backend.service;

import com.resace.backend.dto.TopicDto;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Topic;
import com.resace.backend.repository.QuestionRepository;
import com.resace.backend.repository.TopicRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    public List<TopicDto> getTopics(String paper) {
        List<Topic> topics = paper != null
            ? topicRepository.findByPaper(Paper.valueOf(paper))
            : topicRepository.findAll();
        return topics.stream().map(this::toDto).toList();
    }

    private TopicDto toDto(Topic topic) {
        long count = questionRepository.countByTopicIdAndActiveTrue(topic.getId());
        return new TopicDto(
            topic.getId(),
            topic.getName(),
            topic.getSlug(),
            topic.getPaper().name(),
            topic.getDescription(),
            count
        );
    }
}
