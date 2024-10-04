// src/app/layout.js

import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';

const RootLayout = ({ children }) => {
  return (
    <>
      <Header />
      <main className="main-content">
        {children}
      </main>
      <Footer />
    </>
  );
};

export default RootLayout;
