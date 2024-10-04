// src/app/matchmaking/page.js

import React, { useState } from 'react';
import InputField from '../../components/ui/InputField';
import Button from '../../components/ui/Button';
import { useKundliApi } from '../../hooks/useKundliApi';
import { MATCHMAKING_API } from '../../lib/constants/apiEndpoints';
import { post } from '../../lib/api';

const MatchmakingPage = () => {
  const [formData, setFormData] = useState({
    kundli1Id: '',
    kundli2Id: '',
  });
  const [isLoading, setIsLoading] = useState(false);
  const [matchResult, setMatchResult] = useState(null);
  const { createKundli } = useKundliApi();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const response = await post(MATCHMAKING_API.MATCH_KUNDLIS, formData);
      setMatchResult(response);
    } catch (error) {
      console.error('Matchmaking failed:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="matchmaking-container">
      <h1 className="matchmaking-title">Match Kundlis</h1>
      <form className="matchmaking-form" onSubmit={handleSubmit}>
        <InputField
          label="Kundli 1 ID"
          type="text"
          name="kundli1Id"
          value={formData.kundli1Id}
          onChange={handleChange}
          placeholder="Enter the first Kundli ID"
          required
        />
        <InputField
          label="Kundli 2 ID"
          type="text"
          name="kundli2Id"
          value={formData.kundli2Id}
          onChange={handleChange}
          placeholder="Enter the second Kundli ID"
          required
        />
        <Button type="submit" disabled={isLoading}>
          {isLoading ? 'Matching...' : 'Match Kundlis'}
        </Button>
      </form>

      {matchResult && (
        <div className="match-result">
          <h2 className="result-title">Match Result</h2>
          <p>Compatibility Score: {matchResult.overallCompatibilityScore}</p>
          <p>Compatibility Status: {matchResult.manglikCompatibility ? 'Manglik' : 'Non-Manglik'}</p>
          <p>Language: {matchResult.language}</p>
        </div>
      )}
    </div>
  );
};

export default MatchmakingPage;
