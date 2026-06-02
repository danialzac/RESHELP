import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Layout from '../components/Layout';
import { apiClient } from '../api/client';
import { useAuth } from '../context/AuthContext';

const PAPER_LABELS = {
    PAPER_1: 'Paper 1',
    PAPER_2: 'Paper 2',
};

export default function PracticeHubPage() {
    const { paper } = useParams();
    const { token } = useAuth();
    const navigate = useNavigate();
    const [topics, setTopics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [startingMode, setStartingMode] = useState(null);
    const paperLabel = PAPER_LABELS[paper];

    useEffect(() => {
        if (!paperLabel) {
            navigate('/dashboard', { replace: true });
        }
    }, [navigate, paperLabel]);

    useEffect(() => {
        if (!paperLabel) return;
        setLoading(true);
        apiClient.getTopics(token, paper)
            .then(setTopics)
            .catch(() => {})
            .finally(() => setLoading(false));
    }, [paper, token, paperLabel]);

    async function startTopicPractice(topic) {
        setStartingMode(`topic-${topic.id}`);
        try {
            const questions = await apiClient.getTopicQuestions(token, topic.id);
            navigate('/quiz', {
                state: {
                    questions,
                    paper,
                    mode: 'TOPIC',
                    topicId: topic.id,
                    topicName: topic.name,
                    label: `${PAPER_LABELS[paper]} — ${topic.name}`,
                },
            });
        } catch {
            setStartingMode(null);
        }
    }

    async function startRandomPractice() {
        setStartingMode('random');
        try {
            const questions = await apiClient.getRandomQuestions(token, paper);
            navigate('/quiz', {
                state: {
                    questions,
                    paper,
                    mode: 'RANDOM',
                    topicId: null,
                    label: `${PAPER_LABELS[paper]} — Random Practice`,
                },
            });
        } catch {
            setStartingMode(null);
        }
    }

    async function startMockExam() {
        setStartingMode('mock');
        try {
            const questions = await apiClient.getMockQuestions(token, paper);
            navigate('/quiz', {
                state: {
                    questions,
                    paper,
                    mode: 'MOCK',
                    topicId: null,
                    label: `${PAPER_LABELS[paper]} — Mock Exam`,
                    isMock: true,
                    timeLimitMinutes: 60,
                },
            });
        } catch {
            setStartingMode(null);
        }
    }

    return (
        <Layout>
            <div className="page-container">
                <div className="page-header">
                    <button className="back-btn" onClick={() => navigate('/dashboard')}>
                        ← Dashboard
                    </button>
                    <h1>{paperLabel}</h1>
                    <p className="page-subtitle">Choose how you want to practise.</p>
                </div>

                <div className="practice-mode-row">
                    <button
                        className="mode-card mode-card--random"
                        onClick={startRandomPractice}
                        disabled={startingMode === 'random'}
                    >
                        <span className="mode-icon">⚡</span>
                        <span className="mode-title">Quick Practice</span>
                        <span className="mode-desc">10 random questions from this paper</span>
                    </button>
                    <button
                        className="mode-card mode-card--mock"
                        onClick={startMockExam}
                        disabled={startingMode === 'mock'}
                    >
                        <span className="mode-icon">📋</span>
                        <span className="mode-title">Mock Exam</span>
                        <span className="mode-desc">30 questions, 60 min timed session</span>
                    </button>
                </div>

                <div className="section-heading">
                    <h2>Practice by Topic</h2>
                </div>

                {loading ? (
                    <p className="muted">Loading topics...</p>
                ) : (
                    <div className="topic-list">
                        {topics.map(topic => (
                            <div key={topic.id} className="topic-row">
                                <div className="topic-info">
                                    <h3 className="topic-name">{topic.name}</h3>
                                    <p className="topic-desc">{topic.description}</p>
                                    <span className="topic-count">{topic.questionCount} questions</span>
                                </div>
                                <button
                                    className="button"
                                    onClick={() => startTopicPractice(topic)}
                                    disabled={startingMode === `topic-${topic.id}`}
                                >
                                    {startingMode === `topic-${topic.id}` ? 'Loading...' : 'Start'}
                                </button>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </Layout>
    );
}
