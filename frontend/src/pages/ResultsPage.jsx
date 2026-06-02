import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export default function ResultsPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const { result, label } = location.state || {};

    useEffect(() => {
        if (!result) navigate('/dashboard', { replace: true });
    }, [result, navigate]);

    if (!result) return null;

    const { score, totalQuestions, correctAnswers, paper, mode, reviewedAnswers } = result;

    const scoreColor = score >= 70 ? 'score--pass' : score >= 50 ? 'score--okay' : 'score--fail';
    const scoreMessage =
        score >= 70 ? 'Great work — keep it up!' :
        score >= 50 ? 'Decent result — some areas need more practice.' :
        'Keep going — review the explanations and try again.';

    return (
        <div className="results-shell">
            <div className="results-header">
                <div className="results-meta">
                    <span className="results-label">{label}</span>
                </div>
            </div>

            <div className="results-body">
                <div className={`score-card ${scoreColor}`}>
                    <div className="score-circle">
                        <span className="score-pct">{score}%</span>
                    </div>
                    <div className="score-detail">
                        <p className="score-fraction">{correctAnswers} / {totalQuestions} correct</p>
                        <p className="score-message">{scoreMessage}</p>
                    </div>
                </div>

                <div className="results-actions">
                    <button
                        className="button button-secondary"
                        onClick={() => navigate(`/practice/${paper}`)}
                    >
                        Back to Practice
                    </button>
                    <button
                        className="button"
                        onClick={() => navigate('/dashboard')}
                    >
                        Dashboard
                    </button>
                </div>

                <div className="review-section">
                    <h2>Answer Review</h2>
                    {reviewedAnswers.map((item, idx) => (
                        <div
                            key={item.questionId}
                            className={`review-item ${item.correct ? 'review-item--correct' : 'review-item--wrong'}`}
                        >
                            <div className="review-question-header">
                                <span className={`review-badge ${item.correct ? 'badge--correct' : 'badge--wrong'}`}>
                                    {item.correct ? '✓ Correct' : '✗ Incorrect'}
                                </span>
                                <span className="review-q-num">Q{idx + 1}</span>
                            </div>
                            <p className="review-question-text">{item.questionText}</p>

                            <div className="review-options">
                                {item.answerOptions.map(opt => {
                                    const isSelected = opt.id === item.selectedOptionId;
                                    const isCorrect = opt.correct;
                                    let cls = 'review-opt';
                                    if (isCorrect) cls += ' review-opt--correct';
                                    else if (isSelected && !isCorrect) cls += ' review-opt--wrong';
                                    return (
                                        <div key={opt.id} className={cls}>
                                            <span className="review-opt-label">{opt.optionLabel}</span>
                                            <span className="review-opt-text">{opt.optionText}</span>
                                            {isCorrect && <span className="review-opt-tag">Correct answer</span>}
                                            {isSelected && !isCorrect && <span className="review-opt-tag review-opt-tag--wrong">Your answer</span>}
                                        </div>
                                    );
                                })}
                            </div>

                            {item.explanation && (
                                <div className="review-explanation">
                                    <span className="explanation-label">Explanation</span>
                                    <p>{item.explanation}</p>
                                </div>
                            )}

                            {item.plainEnglish && (
                                <div className="review-plain-english">
                                    <span className="review-panel-label review-panel-label--plain">
                                        💡 Plain English
                                    </span>
                                    <p>{item.plainEnglish}</p>
                                </div>
                            )}

                            {item.memoryRule && (
                                <div className="review-memory-rule">
                                    <span className="review-panel-label review-panel-label--memory">
                                        🧠 Memory Rule
                                    </span>
                                    <p>{item.memoryRule}</p>
                                </div>
                            )}

                            {!item.correct && item.examTrap && (
                                <div className="review-exam-trap">
                                    <span className="review-panel-label review-panel-label--trap">
                                        ⚠️ Examiner Trap
                                    </span>
                                    <p>{item.examTrap}</p>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}
