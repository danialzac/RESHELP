import { Navigate, Route, Routes } from 'react-router-dom';
import DashboardPage from './pages/DashboardPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import PracticeHubPage from './pages/PracticeHubPage';
import QuizSessionPage from './pages/QuizSessionPage';
import ResultsPage from './pages/ResultsPage';
import ProgressPage from './pages/ProgressPage';
import ProtectedRoute from './components/ProtectedRoute';

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/dashboard" replace />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route
                path="/dashboard"
                element={
                    <ProtectedRoute>
                        <DashboardPage />
                    </ProtectedRoute>
                }
            />
            <Route
                path="/practice/:paper"
                element={
                    <ProtectedRoute>
                        <PracticeHubPage />
                    </ProtectedRoute>
                }
            />
            <Route
                path="/quiz"
                element={
                    <ProtectedRoute>
                        <QuizSessionPage />
                    </ProtectedRoute>
                }
            />
            <Route
                path="/results"
                element={
                    <ProtectedRoute>
                        <ResultsPage />
                    </ProtectedRoute>
                }
            />
            <Route
                path="/progress"
                element={
                    <ProtectedRoute>
                        <ProgressPage />
                    </ProtectedRoute>
                }
            />
        </Routes>
    );
}
