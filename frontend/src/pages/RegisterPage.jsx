import { Link } from 'react-router-dom';
import AuthShell from './shared/AuthShell';
import useAuthForm from './shared/useAuthForm';

export default function RegisterPage() {
    const { form, update, submit, pending, error } = useAuthForm('register');

    return (
        <AuthShell
            title="Create account"
            subtitle="Create your free account and start practising."
            footer={
                <p className="muted">
                    Already registered? <Link to="/login">Go to login</Link>
                </p>
            }
        >
            <form className="form-grid" onSubmit={submit}>
                <label>
                    <span>Full name</span>
                    <input required value={form.name} onChange={e => update('name', e.target.value)} />
                </label>
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
                    {pending ? 'Creating...' : 'Register'}
                </button>
            </form>
        </AuthShell>
    );
}
