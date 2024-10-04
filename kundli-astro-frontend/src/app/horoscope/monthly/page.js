// src/app/horoscope/monthly/page.js

"use client"; 
import React, { useEffect, useState } from 'react';
import { HOROSCOPE_API } from '../../../lib/constants/apiEndpoints';
import { get } from '../../../lib/api';
import Loading from '../../../components/Loading';
import Error from '../../../components/Error';
import HoroscopeDisplay from '../../../components/HoroscopeDisplay';
import useAuth from '../../../hooks/useAuth';

const MonthlyHoroscopePage = () => {
  const [horoscope, setHoroscope] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useAuth();

  useEffect(() => {
    const fetchMonthlyHoroscope = async () => {
      try {
        setIsLoading(true);
        const data = await get(HOROSCOPE_API.GENERATE_MONTHLY_HOROSCOPE(user.kundliId));
        setHoroscope(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    if (user && user.kundliId) {
      fetchMonthlyHoroscope();
    }
  }, [user]);

  if (isLoading) return <Loading />;
  if (error) return <Error message={error} />;

  return (
    <div className="monthly-horoscope-page">
      <h1>Monthly Horoscope</h1>
      <HoroscopeDisplay horoscope={horoscope} />
    </div>
  );
};

export default MonthlyHoroscopePage;
