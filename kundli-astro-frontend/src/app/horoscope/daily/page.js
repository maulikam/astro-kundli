// src/app/horoscope/daily/page.js

"use client"; 
import React, { useEffect, useState } from 'react';
import { HOROSCOPE_API } from '../../../lib/constants/apiEndpoints';
import { get } from '../../../lib/api';
import Loading from '../../../components/Loading';
import Error from '../../../components/Error';
import HoroscopeDisplay from '../../../components/HoroscopeDisplay';
import useAuth from '../../../hooks/useAuth';

const DailyHoroscopePage = () => {
  const [horoscope, setHoroscope] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useAuth();

  useEffect(() => {
    const fetchDailyHoroscope = async () => {
      try {
        setIsLoading(true);
        const data = await get(HOROSCOPE_API.GENERATE_DAILY_HOROSCOPE(user.kundliId));
        setHoroscope(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    if (user && user.kundliId) {
      fetchDailyHoroscope();
    }
  }, [user]);

  if (isLoading) return <Loading />;
  if (error) return <Error message={error} />;

  return (
    <div className="daily-horoscope-page">
      <h1>Daily Horoscope</h1>
      <HoroscopeDisplay horoscope={horoscope} />
    </div>
  );
};

export default DailyHoroscopePage;
