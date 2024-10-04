// src/components/forms/MatchmakingForm.js

import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputField from '../ui/InputField';
import Button from '../ui/Button';
import { useKundliApi } from '../../hooks/useKundliApi';

const MatchmakingForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    kundli1Id: '',
    kundli2Id: '',
  });
  const [isLoading, setIsLoading] = useState(false);
  const { matchKundlis } = useKundliApi();

  const handleChange = ({ target: { name, value } }) => {
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const matchResult = await matchKundlis(formData.kundli1Id, formData.kundli2Id);
      if (onSubmit) onSubmit(matchResult);
    } catch (error) {
      console.error('Failed to match kundlis:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <form className="matchmaking-form" onSubmit={handleSubmit}>
      {['kundli1Id', 'kundli2Id'].map((id, index) => (
        <InputField
          key={id}
          label={`Kundli ${index + 1} ID`}
          type="text"
          name={id}
          value={formData[id]}
          onChange={handleChange}
          placeholder={`Enter ${index === 0 ? 'first' : 'second'} Kundli ID`}
          required
        />
      ))}
      <Button type="submit" disabled={isLoading}>
        {isLoading ? 'Matching...' : 'Match Kundlis'}
      </Button>
    </form>
  );
};

MatchmakingForm.propTypes = {
  onSubmit: PropTypes.func,
};

export default MatchmakingForm;
