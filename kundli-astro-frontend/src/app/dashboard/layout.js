// src/app/dashboard/layout.js

import React from 'react';
import PropTypes from 'prop-types';
import Header from '../../components/Header';
import Footer from '../../components/Footer';

const DashboardLayout = ({ children }) => {
  return (
    <div className="dashboard-layout">
      <Header />
      <div className="dashboard-content">
        <aside className="dashboard-sidebar">
          <nav>
            <ul>
              <li><a href="/dashboard">Overview</a></li>
              <li><a href="/dashboard/kundlis">My Kundlis</a></li>
              <li><a href="/dashboard/charts">My Charts</a></li>
              <li><a href="/dashboard/reports">Reports</a></li>
              <li><a href="/dashboard/settings">Settings</a></li>
            </ul>
          </nav>
        </aside>
        <main className="dashboard-main-content">
          {children}
        </main>
      </div>
      <Footer />
    </div>
  );
};

DashboardLayout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default DashboardLayout;
