// src/components/Footer.js

import React from 'react';
import Link from 'next/link';
import { useAuth } from '../hooks/useAuth';

const Footer = () => {
  const { isAuthenticated } = useAuth();

  const links = [
    { href: '/', label: 'Home' },
    { href: '/kundli', label: 'Kundli' },
    { href: '/horoscope', label: 'Horoscope' },
    { href: '/matchmaking', label: 'Matchmaking' },
  ];

  const authLinks = [
    { href: '/login', label: 'Login' },
    { href: '/register', label: 'Register' },
  ];

  const renderLinks = (links) => (
    links.map(({ href, label }) => (
      <li key={href} className="footer-item">
        <Link href={href}>
          <a className="footer-link">{label}</a>
        </Link>
      </li>
    ))
  );

  return (
    <footer className="footer">
      <div className="container">
        <div className="footer-content">
          <div className="footer-links">
            <ul className="footer-list">
              {renderLinks(links)}
              {!isAuthenticated && renderLinks(authLinks)}
            </ul>
          </div>
          <div className="footer-info">
            <p>© {new Date().getFullYear()} Astro Kundli. All rights reserved.</p>
            <p>
              Follow us on{' '}
              <Link href="https://twitter.com/astrokundli">
                <a target="_blank" rel="noopener noreferrer" className="social-link">
                  Twitter
                </a>
              </Link>{' '}
              or{' '}
              <Link href="https://facebook.com/astrokundli">
                <a target="_blank" rel="noopener noreferrer" className="social-link">
                  Facebook
                </a>
              </Link>
            </p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
