// src/app/kundli/[id]/page.js

"use client"; 
import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { KUNDLI_API } from '../../../lib/constants/apiEndpoints';
import { get, put, del } from '../../../lib/api';
import Loading from '../../../components/Loading';
import Error from '../../../components/Error';
import KundliForm from '../../../components/forms/KundliForm';
import useAuth from '../../../hooks/useAuth';

const KundliPage = () => {
  const router = useRouter();
  const { id } = router.query;
  const { user } = useAuth();
  const [kundli, setKundli] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchKundli = async () => {
      try {
        setIsLoading(true);
        const data = await get(KUNDLI_API.GET_KUNDLI_BY_ID(id));
        setKundli(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    if (id) {
      fetchKundli();
    }
  }, [id]);

  const handleUpdate = async (updatedData) => {
    try {
      setIsLoading(true);
      const updatedKundli = await put(KUNDLI_API.UPDATE_KUNDLI(id), updatedData);
      setKundli(updatedKundli);
      setIsEditing(false);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleDelete = async () => {
    try {
      await del(KUNDLI_API.DELETE_KUNDLI(id));
      router.push('/kundli');
    } catch (err) {
      setError(err.message);
    }
  };

  if (isLoading) return <Loading />;
  if (error) return <Error message={error} />;

  return (
    <div className="kundli-page">
      <h1>Kundli Details</h1>
      {isEditing ? (
        <KundliForm kundliData={kundli} onSave={handleUpdate} onCancel={() => setIsEditing(false)} />
      ) : (
        <div className="kundli-details">
          <p><strong>Name:</strong> {kundli.person.fullName}</p>
          <p><strong>Birth Date:</strong> {kundli.birthDate}</p>
          <p><strong>Birth Time:</strong> {kundli.birthTime}</p>
          <p><strong>Birth Place:</strong> {kundli.birthPlace}</p>
          {/* Add more kundli details here */}
          <button onClick={() => setIsEditing(true)}>Edit</button>
          <button onClick={handleDelete}>Delete</button>
        </div>
      )}
    </div>
  );
};

export default KundliPage;
