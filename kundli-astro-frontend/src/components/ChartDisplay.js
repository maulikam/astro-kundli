// src/components/ChartDisplay.js

import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { useKundli } from '../context/KundliContext';
import Loading from './Loading';
import Error from './Error';
import { CHARTS_API } from '../lib/constants/apiEndpoints';
import axiosInstance from '../lib/axiosInstance';

const ChartDisplay = ({ kundliId }) => {
  const { kundlis } = useKundli();
  const [chartData, setChartData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchChartData = async () => {
      setIsLoading(true);
      try {
        const response = await axiosInstance.get(CHARTS_API.GET_CHARTS_BY_KUNDLI_ID(kundliId));
        setChartData(response);
      } catch (error) {
        setError('Failed to fetch chart data.');
      } finally {
        setIsLoading(false);
      }
    };

    if (kundliId) {
      fetchChartData();
    }
  }, [kundliId]);

  const renderLoading = () => <Loading />;
  
  const renderError = () => (
    <Error message={error} retryCallback={() => window.location.reload()} />
  );

  const renderNoData = () => <div>No chart data available.</div>;

  return (
    <div className="chart-display">
      <h2>Astrological Chart for Kundli ID: {kundliId}</h2>
      <div className="chart-container">
        {/* Render the actual chart */}
        {isLoading ? renderLoading() : error ? renderError() : !chartData ? renderNoData() : <img src={chartData.chartImageUrl} alt="Astrological Chart" />}
      </div>
    </div>
  );
};

ChartDisplay.propTypes = {
  kundliId: PropTypes.string.isRequired,
};

export default ChartDisplay;
