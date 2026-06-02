package com.resace.backend.repository;

import com.resace.backend.model.Paper;
import com.resace.backend.model.QuizAttempt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByUserIdOrderByStartedAtDesc(Long userId);
    List<QuizAttempt> findByUserIdAndPaperOrderByStartedAtDesc(Long userId, Paper paper);

    @Query("SELECT COUNT(a) FROM QuizAttempt a WHERE a.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query("SELECT COALESCE(SUM(a.totalQuestions), 0) FROM QuizAttempt a WHERE a.user.id = :userId")
    long sumTotalQuestionsByUserId(@Param("userId") Long userId);

    @Query("SELECT COALESCE(SUM(a.correctAnswers), 0) FROM QuizAttempt a WHERE a.user.id = :userId")
    long sumCorrectAnswersByUserId(@Param("userId") Long userId);

    @Query("SELECT COALESCE(SUM(a.totalQuestions), 0) FROM QuizAttempt a WHERE a.user.id = :userId AND a.paper = :paper")
    long sumTotalQuestionsByUserIdAndPaper(@Param("userId") Long userId, @Param("paper") Paper paper);

    @Query("SELECT COALESCE(SUM(a.correctAnswers), 0) FROM QuizAttempt a WHERE a.user.id = :userId AND a.paper = :paper")
    long sumCorrectAnswersByUserIdAndPaper(@Param("userId") Long userId, @Param("paper") Paper paper);
}
