// src/app/kundli/page.js

import React, { useState, useEffect } from 'react';
import { KUNDLI_API } from '../../lib/constants/apiEndpoints';
import { get, post } from '../../lib/api';
import Loading from '../../components/Loading';
import Error from '../../components/Error';
import KundliForm from '../../components/forms/KundliForm';
import { useAuth } from '../../hooks/useAuth';
import { useRouter } from 'next/router';

const KundliMainPage = () => {
  const [kundlis, setKundlis] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useAuth();
  const router = useRouter();

  useEffect(() => {
    const fetchKundlis = async () => {
      try {
        setIsLoading(true);
        const data = await get(KUNDLI_API.GET_ALL_KUNDLIS);
        setKundlis(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    fetchKundlis();
  }, []);

  const handleCreateKundli = async (kundliData) => {
    try {
      setIsLoading(true);
      const newKundli = await post(KUNDLI_API.CREATE_KUNDLI, kundliData);
      setKundlis((prevKundlis) => [...prevKundlis, newKundli]);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleKundliClick = (kundliId) => {
    router.push(`/kundli/${kundliId}`);
  };

  if (isLoading) return <Loading />;
  if (error) return <Error message={error} />;

  return (
    <div className="kundli-main-page">
      <h1>My Kundlis</h1>
      <KundliForm onSave={handleCreateKundli} />
      <div className="kundli-list">
        {kundlis.map((kundli) => (
          <div key={kundli.id} className="kundli-item" onClick={() => handleKundliClick(kundli.id)}>
            <p><strong>Name:</strong> {kundli.person.fullName}</p>
            <p><strong>Birth Date:</strong> {kundli.birthDate}</p>
            <p><strong>Birth Place:</strong> {kundli.birthPlace}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default KundliMainPage;
