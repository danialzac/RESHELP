package com.resace.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Paper paper;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(nullable = false, length = 1000)
    private String questionText;

    @Column(length = 2000)
    private String explanation;

    /** Plain-English version of the concept (max ~100 words, no jargon). */
    @Column(length = 2000)
    private String plainEnglish;

    /** Memory shortcut: "If you see X, think Y" — one line. */
    @Column(length = 500)
    private String memoryRule;

    /** Examiner trap: why students commonly pick the wrong answer. */
    @Column(length = 1000)
    private String examTrap;

    /** How frequently this question type appears in the exam. HIGH / MEDIUM / LOW */
    @Column(length = 20)
    private String examFrequency;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<AnswerOption> answerOptions = new ArrayList<>();
}
