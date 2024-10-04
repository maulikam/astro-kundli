// src/app/kundli/layout.js

import React from 'react';
import Header from '../../components/Header';
import Footer from '../../components/Footer';

const KundliLayout = ({ children }) => {
  return (
    <>
      <Header />
      <main className="kundli-layout">
        {children}
      </main>
      <Footer />
    </>
  );
};

export default KundliLayout;
