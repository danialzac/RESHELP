package com.resace.backend.repository;

import com.resace.backend.model.Paper;
import com.resace.backend.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTopicIdAndActiveTrue(Long topicId);
    List<Question> findByPaperAndActiveTrue(Paper paper);

    @Query(value = "SELECT * FROM questions WHERE paper = :paper AND active = true ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomByPaper(@Param("paper") String paper, @Param("limit") int limit);

    long countByTopicIdAndActiveTrue(Long topicId);
}
