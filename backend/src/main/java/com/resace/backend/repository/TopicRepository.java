package com.resace.backend.repository;

import com.resace.backend.model.Paper;
import com.resace.backend.model.Topic;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByPaper(Paper paper);
    Optional<Topic> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
