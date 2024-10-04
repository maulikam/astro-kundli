import React, { useContext } from 'react';
import AuthContext from '../context/AuthContext';

/**
 * Custom hook to provide authentication-related operations and state.
 */
const useAuth = () => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }

  return context;
};

export default useAuth; // Ensure this line is present
