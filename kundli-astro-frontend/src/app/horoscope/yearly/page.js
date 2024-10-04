// src/app/horoscope/yearly/page.js

import React, { useEffect, useState } from 'react';
import { HOROSCOPE_API } from '../../../lib/constants/apiEndpoints';
import { get } from '../../../lib/api';
import Loading from '../../../components/Loading';
import Error from '../../../components/Error';
import HoroscopeDisplay from '../../../components/HoroscopeDisplay';
import { useAuth } from '../../../hooks/useAuth';

const YearlyHoroscopePage = () => {
  const [horoscope, setHoroscope] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useAuth();

  useEffect(() => {
    const fetchYearlyHoroscope = async () => {
      try {
        setIsLoading(true);
        const data = await get(HOROSCOPE_API.GENERATE_YEARLY_HOROSCOPE(user.kundliId));
        setHoroscope(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    if (user && user.kundliId) {
      fetchYearlyHoroscope();
    }
  }, [user]);

  if (isLoading) return <Loading />;
  if (error) return <Error message={error} />;

  return (
    <div className="yearly-horoscope-page">
      <h1>Yearly Horoscope</h1>
      <HoroscopeDisplay horoscope={horoscope} />
    </div>
  );
};

export default YearlyHoroscopePage;
