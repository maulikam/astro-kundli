// src/components/forms/KundliForm.js

import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputField from '../ui/InputField';
import Button from '../ui/Button';
import { useKundli } from '../../hooks/useKundli';

const KundliForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    fullName: '',
    birthDate: '',
    birthTime: '',
    birthPlace: '',
  });
  const [isLoading, setIsLoading] = useState(false);
  const { createKundli } = useKundli();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      await createKundli(formData);
      onSubmit && onSubmit(formData);
    } catch (error) {
      console.error('Failed to generate Kundli:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <form className="kundli-form" onSubmit={handleFormSubmit}>
      {renderInputFields(formData, handleChange)}
      <Button type="submit" disabled={isLoading}>
        {isLoading ? 'Generating...' : 'Generate Kundli'}
      </Button>
    </form>
  );
};

const renderInputFields = (formData, handleChange) => (
  <>
    <InputField
      label="Full Name"
      type="text"
      name="fullName"
      value={formData.fullName}
      onChange={handleChange}
      placeholder="Enter your full name"
      required
    />
    <InputField
      label="Birth Date"
      type="date"
      name="birthDate"
      value={formData.birthDate}
      onChange={handleChange}
      required
    />
    <InputField
      label="Birth Time"
      type="time"
      name="birthTime"
      value={formData.birthTime}
      onChange={handleChange}
      required
    />
    <InputField
      label="Birth Place"
      type="text"
      name="birthPlace"
      value={formData.birthPlace}
      onChange={handleChange}
      placeholder="Enter your birth place"
      required
    />
  </>
);

KundliForm.propTypes = {
  onSubmit: PropTypes.func,
};

export default KundliForm;
