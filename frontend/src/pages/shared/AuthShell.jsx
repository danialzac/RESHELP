export default function AuthShell({ title, subtitle, children, footer }) {
    return (
        <div className="auth-shell">
            <div className="auth-card">
                <p className="eyebrow">RES Exam Bank</p>
                <h1>{title}</h1>
                <p className="muted">{subtitle}</p>
                {children}
                {footer}
            </div>
        </div>
    );
}
