package com.resace.backend.repository;

import com.resace.backend.model.QuizAttemptAnswer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizAttemptAnswerRepository extends JpaRepository<QuizAttemptAnswer, Long> {
    List<QuizAttemptAnswer> findByAttemptId(Long attemptId);

    @Query("""
        SELECT qaa FROM QuizAttemptAnswer qaa
        JOIN qaa.attempt a
        JOIN a.user u
        WHERE u.id = :userId AND a.topic.id = :topicId
    """)
    List<QuizAttemptAnswer> findByUserIdAndTopicId(@Param("userId") Long userId, @Param("topicId") Long topicId);
}
