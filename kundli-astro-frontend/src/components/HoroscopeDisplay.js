// src/components/HoroscopeDisplay.js

import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import Loading from './Loading';
import Error from './Error';
import { HOROSCOPE_API } from '../lib/constants/apiEndpoints';
import axiosInstance from '../lib/axiosInstance';

const HoroscopeDisplay = ({ kundliId, period = 'daily' }) => {
  const [horoscopeData, setHoroscopeData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchHoroscope = async () => {
      setIsLoading(true);
      try {
        let endpoint;
        if (period === 'daily') {
          endpoint = HOROSCOPE_API.GENERATE_DAILY_HOROSCOPE(kundliId);
        } else if (period === 'monthly') {
          endpoint = HOROSCOPE_API.GENERATE_MONTHLY_HOROSCOPE(kundliId);
        } else if (period === 'yearly') {
          endpoint = HOROSCOPE_API.GENERATE_YEARLY_HOROSCOPE(kundliId);
        } else {
          throw new Error('Invalid horoscope period specified.');
        }

        const response = await axiosInstance.post(endpoint);
        setHoroscopeData(response);
      } catch (error) {
        setError('Failed to fetch horoscope data.');
      } finally {
        setIsLoading(false);
      }
    };

    if (kundliId) {
      fetchHoroscope();
    }
  }, [kundliId, period]);

  if (isLoading) {
    return <Loading />;
  }

  if (error) {
    return <Error message={error} retryCallback={() => window.location.reload()} />;
  }

  if (!horoscopeData) {
    return <div>No horoscope data available.</div>;
  }

  return (
    <div className="horoscope-display">
      <h2>{`${period.charAt(0).toUpperCase() + period.slice(1)} Horoscope for Kundli ID: ${kundliId}`}</h2>
      <div className="horoscope-details">
        <p><strong>Date:</strong> {horoscopeData.horoscopeDate}</p>
        <p><strong>Predictions:</strong> {horoscopeData.predictions}</p>
        <p><strong>Planetary Positions:</strong> {horoscopeData.planetaryPositions}</p>
      </div>
    </div>
  );
};

HoroscopeDisplay.propTypes = {
  kundliId: PropTypes.string.isRequired,
  period: PropTypes.oneOf(['daily', 'monthly', 'yearly']),
};

export default HoroscopeDisplay;
