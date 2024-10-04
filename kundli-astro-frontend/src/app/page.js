// src/app/page.js

import React from 'react';
import Link from 'next/link';
import Button from '../components/ui/Button';
import { useAuth } from '../hooks/useAuth';

const HomePage = () => {
  const { isAuthenticated } = useAuth();

  return (
    <div className="homepage-container">
      <header className="hero-section">
        <h1 className="hero-title">Welcome to Astro Kundli</h1>
        <p className="hero-description">
          Explore the power of astrology with our Kundli generator, horoscope predictions, and matchmaking services.
        </p>
        {!isAuthenticated && (
          <div className="auth-buttons">
            <Link href="/register">
              <Button>Register</Button>
            </Link>
            <Link href="/login">
              <Button>Login</Button>
            </Link>
          </div>
        )}
      </header>
      <section className="features-section">
        <h2 className="section-title">Our Features</h2>
        <div className="features-list">
          <div className="feature-item">
            <h3>Kundli Generator</h3>
            <p>Generate accurate Kundli details to explore your astrological blueprint.</p>
            <Link href="/kundli">
              <a className="feature-link">Explore Kundli</a>
            </Link>
          </div>
          <div className="feature-item">
            <h3>Daily, Monthly, and Yearly Horoscopes</h3>
            <p>Get personalized horoscope predictions to guide you in making better decisions.</p>
            <Link href="/horoscope">
              <a className="feature-link">View Horoscope</a>
            </Link>
          </div>
          <div className="feature-item">
            <h3>Matchmaking</h3>
            <p>Find out compatibility between Kundlis for marriage and relationships.</p>
            <Link href="/matchmaking">
              <a className="feature-link">Match Kundlis</a>
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
