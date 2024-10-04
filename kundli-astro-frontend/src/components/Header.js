// src/components/Header.js
import React from 'react';
import Link from 'next/link';
import useAuth from '../hooks/useAuth'; // Updated to make sure it's imported from the hooks

const Header = () => {
  const { user, isAuthenticated, logout } = useAuth();

  const renderAuthenticatedLinks = () => (
    <>
      <li className="nav-item">
        <Link href="/dashboard">
          <a className="nav-link">Dashboard</a>
        </Link>
      </li>
      <li className="nav-item">
        <button onClick={logout} className="nav-link button">
          Logout
        </button>
      </li>
      <li className="nav-item">
        <span className="user-greeting">Hello, {user.fullName}</span>
      </li>
    </>
  );

  const renderUnauthenticatedLinks = () => (
    <>
      <li className="nav-item">
        <Link href="/login">
          <a className="nav-link">Login</a>
        </Link>
      </li>
      <li className="nav-item">
        <Link href="/register">
          <a className="nav-link">Register</a>
        </Link>
      </li>
    </>
  );

  return (
    <header className="header">
      <div className="container">
        <Link href="/">
          <a className="logo">Astro Kundli</a>
        </Link>
        <nav className="nav">
          <ul className="nav-list">
            <li className="nav-item">
              <Link href="/">
                <a className="nav-link">Home</a>
              </Link>
            </li>
            <li className="nav-item">
              <Link href="/kundli">
                <a className="nav-link">Kundli</a>
              </Link>
            </li>
            <li className="nav-item">
              <Link href="/horoscope">
                <a className="nav-link">Horoscope</a>
              </Link>
            </li>
            <li className="nav-item">
              <Link href="/matchmaking">
                <a className="nav-link">Matchmaking</a>
              </Link>
            </li>
            {isAuthenticated ? renderAuthenticatedLinks() : renderUnauthenticatedLinks()}
          </ul>
        </nav>
      </div>
    </header>
  );
};

export default Header;
