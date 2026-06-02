import { useEffect, useRef, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { apiClient } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function QuizSessionPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const { token } = useAuth();

    const state = location.state;

    useEffect(() => {
        if (!state?.questions?.length) {
            navigate('/dashboard', { replace: true });
        }
    }, [state, navigate]);

    const { questions = [], paper, mode, topicId, label, isMock, timeLimitMinutes } = state || {};

    const [currentIndex, setCurrentIndex] = useState(0);
    const [selected, setSelected] = useState({});
    const [submitting, setSubmitting] = useState(false);
    const [timeLeft, setTimeLeft] = useState(isMock ? timeLimitMinutes * 60 : null);

    const timerRef = useRef(null);

    useEffect(() => {
        if (!isMock || timeLeft === null) return;
        if (timeLeft <= 0) {
            handleSubmit();
            return;
        }
        timerRef.current = setTimeout(() => setTimeLeft(t => t - 1), 1000);
        return () => clearTimeout(timerRef.current);
    }, [timeLeft, isMock]);

    if (!questions.length) return null;

    const question = questions[currentIndex];
    const total = questions.length;
    const isLast = currentIndex === total - 1;
    const allAnswered = questions.every(q => selected[q.id] !== undefined);

    function selectOption(optionId) {
        setSelected(prev => ({ ...prev, [question.id]: optionId }));
    }

    function goNext() {
        if (currentIndex < total - 1) setCurrentIndex(i => i + 1);
    }

    function goPrev() {
        if (currentIndex > 0) setCurrentIndex(i => i - 1);
    }

    async function handleSubmit() {
        if (submitting) return;
        clearTimeout(timerRef.current);
        setSubmitting(true);
        try {
            const answers = questions.map(q => ({
                questionId: q.id,
                selectedOptionId: selected[q.id] ?? null,
            }));
            const result = await apiClient.submitAttempt(token, {
                paper,
                mode,
                topicId: topicId || null,
                answers,
            });
            navigate('/results', { state: { result, label } });
        } catch {
            setSubmitting(false);
        }
    }

    const minutes = timeLeft !== null ? Math.floor(timeLeft / 60) : null;
    const seconds = timeLeft !== null ? timeLeft % 60 : null;
    const timerWarning = timeLeft !== null && timeLeft <= 300;

    return (
        <div className="quiz-shell">
            <div className="quiz-header">
                <div className="quiz-meta">
                    <span className="quiz-label">{label}</span>
                    <span className="quiz-progress">
                        {currentIndex + 1} / {total}
                    </span>
                </div>
                {isMock && timeLeft !== null && (
                    <span className={`quiz-timer${timerWarning ? ' quiz-timer--warning' : ''}`}>
                        {String(minutes).padStart(2, '0')}:{String(seconds).padStart(2, '0')}
                    </span>
                )}
            </div>

            <div className="quiz-progress-bar">
                <div
                    className="quiz-progress-fill"
                    style={{ width: `${((currentIndex + 1) / total) * 100}%` }}
                />
            </div>

            <div className="quiz-body">
                <div className="question-card">
                    <p className="question-number">Question {currentIndex + 1}</p>
                    <p className="question-text">{question.questionText}</p>

                    <div className="options-list">
                        {question.answerOptions.map(opt => {
                            const isSelected = selected[question.id] === opt.id;
                            return (
                                <button
                                    key={opt.id}
                                    className={`option-btn${isSelected ? ' option-btn--selected' : ''}`}
                                    onClick={() => selectOption(opt.id)}
                                    type="button"
                                >
                                    <span className="option-label">{opt.optionLabel}</span>
                                    <span className="option-text">{opt.optionText}</span>
                                </button>
                            );
                        })}
                    </div>
                </div>

                <div className="quiz-nav">
                    <button
                        className="button button-secondary"
                        onClick={goPrev}
                        disabled={currentIndex === 0}
                    >
                        Previous
                    </button>

                    {!isLast && (
                        <button
                            className="button"
                            onClick={goNext}
                        >
                            Next
                        </button>
                    )}

                    {isLast && (
                        <button
                            className="button button-submit"
                            onClick={handleSubmit}
                            disabled={submitting}
                        >
                            {submitting ? 'Submitting...' : 'Submit'}
                        </button>
                    )}
                </div>

                <div className="question-dots">
                    {questions.map((q, i) => (
                        <button
                            key={q.id}
                            className={`dot${i === currentIndex ? ' dot--current' : ''}${selected[q.id] !== undefined ? ' dot--answered' : ''}`}
                            onClick={() => setCurrentIndex(i)}
                            title={`Q${i + 1}`}
                            type="button"
                        />
                    ))}
                </div>
            </div>
        </div>
    );
}
