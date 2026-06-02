import { Link } from 'react-router-dom';
import AuthShell from './shared/AuthShell';
import useAuthForm from './shared/useAuthForm';

export default function LoginPage() {
    const { form, update, submit, pending, error } = useAuthForm('login');

    return (
        <AuthShell
            title="Welcome back"
            subtitle="Log in to continue your RES exam revision."
            footer={
                <p className="muted">
                    Need an account? <Link to="/register">Create one</Link>
                </p>
            }
        >
            <form className="form-grid" onSubmit={submit}>
                <label>
                    <span>Email</span>
                    <input type="email" required value={form.email} onChange={e => update('email', e.target.value)} />
                </label>
                <label>
                    <span>Password</span>
                    <input
                        type="password"
                        required
                        value={form.password}
                        onChange={e => update('password', e.target.value)}
                    />
                </label>
                {error ? <p className="error-text">{error}</p> : null}
                <button className="button" type="submit" disabled={pending}>
                    {pending ? 'Signing in...' : 'Login'}
                </button>
            </form>
        </AuthShell>
    );
}
