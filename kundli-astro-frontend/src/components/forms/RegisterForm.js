// src/components/forms/RegisterForm.js

import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputField from '../ui/InputField';
import Button from '../ui/Button';
import useAuth from '../../hooks/useAuth';

// New reusable FormInput component to minimize redundancy
const FormInput = ({ label, type, name, value, onChange, placeholder, required }) => (
  <InputField
    label={label}
    type={type}
    name={name}
    value={value}
    onChange={onChange}
    placeholder={placeholder}
    required={required}
  />
);

const RegisterForm = ({ onSuccess }) => {
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    password: '',
    phoneNumber: '',
  });
  const [isLoading, setIsLoading] = useState(false);
  const { register } = useAuth();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      await register(formData);
      if (onSuccess) {
        onSuccess();
      }
    } catch (error) {
      console.error('Registration failed:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <form className="register-form" onSubmit={handleSubmit}>
      <FormInput
        label="Full Name"
        type="text"
        name="fullName"
        value={formData.fullName}
        onChange={handleChange}
        placeholder="Enter your full name"
        required
      />
      <FormInput
        label="Email"
        type="email"
        name="email"
        value={formData.email}
        onChange={handleChange}
        placeholder="Enter your email"
        required
      />
      <FormInput
        label="Password"
        type="password"
        name="password"
        value={formData.password}
        onChange={handleChange}
        placeholder="Enter your password"
        required
      />
      <FormInput
        label="Phone Number"
        type="tel"
        name="phoneNumber"
        value={formData.phoneNumber}
        onChange={handleChange}
        placeholder="Enter your phone number"
      />
      <Button type="submit" disabled={isLoading}>
        {isLoading ? 'Registering...' : 'Register'}
      </Button>
    </form>
  );
};

RegisterForm.propTypes = {
  onSuccess: PropTypes.func,
};

RegisterForm.defaultProps = {
  onSuccess: () => {
    console.log('Registration successful.'); // Default success action (e.g., logging or redirection can be added here)
  },
};

export default RegisterForm;
