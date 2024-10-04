// src/app/charts/layout.js

import React from 'react';
import PropTypes from 'prop-types';
import Header from '../../components/Header';
import Footer from '../../components/Footer';

const ChartsLayout = ({ children }) => {
  return (
    <div className="charts-layout">
      <Header />
      <main className="charts-content">
        {children}
      </main>
      <Footer />
    </div>
  );
};

ChartsLayout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default ChartsLayout;
