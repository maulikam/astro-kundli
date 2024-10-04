// src/app/dashboard/page.js

import React from 'react';
import { useAuth } from '../../hooks/useAuth';
import HoroscopeDisplay from '../../components/HoroscopeDisplay';
import Button from '../../components/ui/Button';

const DashboardPage = () => {
  const { user } = useAuth();

  return (
    <div className="dashboard-page">
      <h1>Welcome, {user?.fullName || 'User'}!</h1>
      <div className="dashboard-widgets">
        <div className="recent-activity">
          <h2>Recent Activities</h2>
          <p>You recently generated a Natal Chart on October 10th.</p>
          <p>You updated your Kundli on October 8th.</p>
        </div>

        <div className="horoscope-overview">
          <h2>Your Horoscope Today</h2>
          <HoroscopeDisplay type="daily" kundliId={user?.kundliId} />
        </div>
      </div>

      <div className="actions">
        <Button onClick={() => window.location.href = '/dashboard/kundlis'}>
          Manage Kundlis
        </Button>
        <Button onClick={() => window.location.href = '/dashboard/charts'}>
          View Charts
        </Button>
      </div>
    </div>
  );
};

export default DashboardPage;
