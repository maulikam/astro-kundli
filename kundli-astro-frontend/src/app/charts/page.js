// src/app/charts/page.js

import React from 'react';
import Link from 'next/link';
import ChartDisplay from '../../components/ChartDisplay';
import Button from '../../components/ui/Button';

const ChartsPage = () => {
  return (
    <div className="charts-page">
      <h1>Astrology Charts Overview</h1>
      <p>Select a chart to view detailed astrological analysis:</p>
      
      <div className="chart-options">
        <ChartDisplay chartType="Natal Chart" description="Detailed chart based on birth details." />
        <ChartDisplay chartType="Transit Chart" description="Shows the current planetary movements." />
        <ChartDisplay chartType="Synastry Chart" description="Analyze compatibility between two people." />
      </div>

      <div className="navigation-buttons">
        <Link href="/charts/natal">
          <Button>View Natal Chart</Button>
        </Link>
        <Link href="/charts/transit">
          <Button>View Transit Chart</Button>
        </Link>
        <Link href="/charts/synastry">
          <Button>View Synastry Chart</Button>
        </Link>
      </div>
    </div>
  );
};

export default ChartsPage;
