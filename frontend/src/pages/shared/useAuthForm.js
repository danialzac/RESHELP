import { useState } from 'react';
import { useAuth } from '../../context/AuthContext';

const initialForm = {
    name: '',
    email: '',
    password: '',
};

export default function useAuthForm(mode) {
    const [form, setForm] = useState(initialForm);
    const [pending, setPending] = useState(false);
    const [error, setError] = useState('');
    const { login, register } = useAuth();

    function update(field, value) {
        setForm(current => ({ ...current, [field]: value }));
    }

    async function submit(event) {
        event.preventDefault();
        try {
            setPending(true);
            setError('');
            if (mode === 'login') {
                await login({ email: form.email, password: form.password });
            } else {
                await register(form);
            }
        } catch (err) {
            setError(err.message);
        } finally {
            setPending(false);
        }
    }

    return { form, update, submit, pending, error };
}
