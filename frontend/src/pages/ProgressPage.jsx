import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Layout from '../components/Layout';
import { apiClient } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function ProgressPage() {
    const { token } = useAuth();
    const navigate = useNavigate();
    const [progress, setProgress] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        apiClient.getProgressSummary(token)
            .then(setProgress)
            .catch(() => {})
            .finally(() => setLoading(false));
    }, [token]);

    return (
        <Layout>
            <div className="page-container">
                <div className="page-header">
                    <h1>Your Progress</h1>
                    <p className="page-subtitle">A summary of your revision sessions.</p>
                </div>

                {loading && <p className="muted">Loading...</p>}

                {!loading && (!progress || progress.totalAttempts === 0) && (
                    <div className="empty-state">
                        <p>No sessions yet. Complete a practice session to see your progress here.</p>
                        <button className="button mt-16" onClick={() => navigate('/dashboard')}>
                            Start Practising
                        </button>
                    </div>
                )}

                {progress && progress.totalAttempts > 0 && (
                    <>
                        <div className="stats-row">
                            <div className="stat-chip">
                                <span className="stat-value">{progress.totalAttempts}</span>
                                <span className="stat-label">Sessions</span>
                            </div>
                            <div className="stat-chip">
                                <span className="stat-value">{progress.totalQuestionsAttempted}</span>
                                <span className="stat-label">Questions Attempted</span>
                            </div>
                            <div className="stat-chip">
                                <span className="stat-value">{progress.overallAccuracy}%</span>
                                <span className="stat-label">Overall Accuracy</span>
                            </div>
                        </div>

                        <div className="card">
                            <h2>By Paper</h2>
                            <div className="progress-paper-grid">
                                {[
                                    { label: 'Paper 1', stats: progress.paper1Stats },
                                    { label: 'Paper 2', stats: progress.paper2Stats },
                                ].map(({ label, stats }) => (
                                    <div key={label} className="progress-paper-card">
                                        <h3>{label}</h3>
                                        <div className="progress-paper-row">
                                            <span>{stats.questionsAttempted} attempted</span>
                                            <span>{stats.correct} correct</span>
                                        </div>
                                        <div className="accuracy-bar-wrap mt-8">
                                            <div
                                                className="accuracy-bar"
                                                style={{ width: `${stats.accuracy}%` }}
                                            />
                                        </div>
                                        <span className="accuracy-label">{stats.accuracy}% accuracy</span>
                                    </div>
                                ))}
                            </div>
                        </div>

                        {progress.topicStats?.length > 0 && (
                            <div className="card">
                                <h2>By Topic</h2>
                                <div className="table-wrap">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Topic</th>
                                                <th>Paper</th>
                                                <th>Attempted</th>
                                                <th>Correct</th>
                                                <th>Accuracy</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {progress.topicStats.map(t => (
                                                <tr key={t.topicId}>
                                                    <td>{t.topicName}</td>
                                                    <td className="muted">{t.paper.replace('_', ' ')}</td>
                                                    <td>{t.attempted}</td>
                                                    <td>{t.correct}</td>
                                                    <td>
                                                        <span className={`accuracy-badge ${t.accuracy >= 70 ? 'badge-pass' : t.accuracy >= 50 ? 'badge-okay' : 'badge-fail'}`}>
                                                            {t.accuracy}%
                                                        </span>
                                                    </td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        )}
                    </>
                )}
            </div>
        </Layout>
    );
}
