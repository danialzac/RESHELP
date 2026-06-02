import { Link, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Layout({ children }) {
    const { user, logout } = useAuth();
    const location = useLocation();

    const navLinks = [
        { to: '/dashboard', label: 'Dashboard' },
        { to: '/progress', label: 'Progress' },
    ];

    return (
        <div className="app-layout">
            <nav className="topnav">
                <Link to="/dashboard" className="brand">
                    RES Exam Bank
                </Link>
                <div className="nav-links">
                    {navLinks.map(link => (
                        <Link
                            key={link.to}
                            to={link.to}
                            className={`nav-link${location.pathname === link.to ? ' nav-link--active' : ''}`}
                        >
                            {link.label}
                        </Link>
                    ))}
                </div>
                <div className="nav-actions">
                    <span className="user-pill">{user?.name || user?.email}</span>
                    <button className="button button-ghost" onClick={logout}>
                        Logout
                    </button>
                </div>
            </nav>
            <main className="page-body">{children}</main>
        </div>
    );
}
