const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

async function request(path, options = {}) {
    const response = await fetch(`${API_BASE_URL}${path}`, {
        headers: {
            'Content-Type': 'application/json',
            ...(options.token ? { Authorization: `Bearer ${options.token}` } : {}),
            ...options.headers,
        },
        method: options.method || 'GET',
        body: options.body ? JSON.stringify(options.body) : undefined,
    });

    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || 'Request failed');
    }

    if (response.status === 204) return null;
    return response.json();
}

export const apiClient = {
    login: credentials => request('/auth/login', { method: 'POST', body: credentials }),
    register: payload => request('/auth/register', { method: 'POST', body: payload }),

    getTopics: (token, paper) =>
        request(`/topics${paper ? `?paper=${paper}` : ''}`, { token }),

    getTopicQuestions: (token, topicId) =>
        request(`/practice/topics/${topicId}/questions`, { token }),

    getRandomQuestions: (token, paper) =>
        request(`/practice/random?paper=${paper}`, { token }),

    getMockQuestions: (token, paper) =>
        request(`/practice/mock?paper=${paper}`, { token }),

    submitAttempt: (token, payload) =>
        request('/attempts', { method: 'POST', token, body: payload }),

    getProgressSummary: token =>
        request('/progress/summary', { token }),
};
