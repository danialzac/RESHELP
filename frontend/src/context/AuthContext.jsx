import { createContext, useContext, useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { apiClient } from '../api/client';

const AuthContext = createContext(null);
const STORAGE_KEY = 'res-exam-bank-auth';

export function AuthProvider({ children }) {
    const [session, setSession] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const raw = localStorage.getItem(STORAGE_KEY);
        if (raw) {
            setSession(JSON.parse(raw));
        }
        setLoading(false);
    }, []);

    const persist = nextSession => {
        setSession(nextSession);
        if (nextSession) {
            localStorage.setItem(STORAGE_KEY, JSON.stringify(nextSession));
        } else {
            localStorage.removeItem(STORAGE_KEY);
        }
    };

    const login = async credentials => {
        const data = await apiClient.login(credentials);
        persist(data);
        navigate('/dashboard');
    };

    const register = async payload => {
        const data = await apiClient.register(payload);
        persist(data);
        navigate('/dashboard');
    };

    const logout = () => {
        persist(null);
        navigate('/login');
    };

    const value = useMemo(
        () => ({
            session,
            token: session?.token,
            user: session?.user,
            loading,
            login,
            register,
            logout,
        }),
        [session, loading]
    );

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within AuthProvider');
    }
    return context;
}
