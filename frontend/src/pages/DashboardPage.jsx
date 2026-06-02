import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Layout from '../components/Layout';
import { apiClient } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function DashboardPage() {
    const { token, user } = useAuth();
    const navigate = useNavigate();
    const [progress, setProgress] = useState(null);

    useEffect(() => {
        apiClient.getProgressSummary(token)
            .then(setProgress)
            .catch(() => {});
    }, [token]);

    const papers = [
        {
            id: 'PAPER_1',
            label: 'Paper 1',
            description: 'Real estate legislation, property types, housing policies, market overview, and taxation.',
            color: 'paper-card--blue',
        },
        {
            id: 'PAPER_2',
            label: 'Paper 2',
            description: 'Agency law, marketing, negotiation, professional ethics, and client management.',
            color: 'paper-card--green',
        },
    ];

    const stats = progress
        ? [
              { label: 'Total Sessions', value: progress.totalAttempts },
              { label: 'Questions Attempted', value: progress.totalQuestionsAttempted },
              { label: 'Overall Accuracy', value: `${progress.overallAccuracy}%` },
          ]
        : null;

    return (
        <Layout>
            <div className="page-container">
                <div className="page-header">
                    <h1>Welcome back{user?.name ? `, ${user.name}` : ''}</h1>
                    <p className="page-subtitle">Choose a paper to start practising.</p>
                </div>

                {stats && (
                    <div className="stats-row">
                        {stats.map(s => (
                            <div key={s.label} className="stat-chip">
                                <span className="stat-value">{s.value}</span>
                                <span className="stat-label">{s.label}</span>
                            </div>
                        ))}
                    </div>
                )}

                <div className="paper-grid">
                    {papers.map(paper => (
                        <div key={paper.id} className={`paper-card ${paper.color}`}>
                            <div className="paper-card-content">
                                <p className="paper-tag">Singapore RES Exam</p>
                                <h2>{paper.label}</h2>
                                <p className="paper-description">{paper.description}</p>
                            </div>
                            <button
                                className="button button-white"
                                onClick={() => navigate(`/practice/${paper.id}`)}
                            >
                                Start Practising
                            </button>
                        </div>
                    ))}
                </div>

                {progress && progress.totalAttempts > 0 && (
                    <div className="card">
                        <h3>Your Progress Overview</h3>
                        <div className="progress-mini-grid">
                            <div className="progress-mini-item">
                                <span className="progress-mini-label">Paper 1</span>
                                <div className="accuracy-bar-wrap">
                                    <div
                                        className="accuracy-bar"
                                        style={{ width: `${progress.paper1Stats.accuracy}%` }}
                                    />
                                </div>
                                <span className="progress-mini-pct">{progress.paper1Stats.accuracy}%</span>
                            </div>
                            <div className="progress-mini-item">
                                <span className="progress-mini-label">Paper 2</span>
                                <div className="accuracy-bar-wrap">
                                    <div
                                        className="accuracy-bar accuracy-bar--green"
                                        style={{ width: `${progress.paper2Stats.accuracy}%` }}
                                    />
                                </div>
                                <span className="progress-mini-pct">{progress.paper2Stats.accuracy}%</span>
                            </div>
                        </div>
                        <button
                            className="button button-secondary mt-16"
                            onClick={() => navigate('/progress')}
                        >
                            View Full Progress
                        </button>
                    </div>
                )}
            </div>
        </Layout>
    );
}
