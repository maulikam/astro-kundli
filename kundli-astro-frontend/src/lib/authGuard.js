// src/lib/authGuard.js

import axios from './axiosInstance';
import { AUTH_API } from './constants/apiEndpoints';
import useAuth from '../hooks/useAuth'; // Ensure this line is correct
import React from 'react';

/**
 * Check if the user is authenticated
 * @returns {boolean} - True if authenticated, false otherwise
 */
export const isAuthenticated = () => {
  const token = localStorage.getItem('auth_token');
  return !!token;
};

/**
 * Protect a route by redirecting to login if not authenticated
 * This function can be used for backend route protection
 * @param {Function} next - The function to call if authenticated
 * @param {Function} redirect - The function to call to redirect to login
 */
export const protectRoute = (next, redirect) => {
  if (isAuthenticated()) {
    next();
  } else {
    redirect('/login');
  }
};

/**
 * Fetch the current authenticated user
 * @returns {Promise<Object>} - The user data
 */
export const fetchCurrentUser = async () => {
  try {
    const response = await axios.get(AUTH_API.AUTHENTICATE);
    return response.data;
  } catch (error) {
    console.error('Failed to fetch current user:', error);
    throw error;
  }
};

/**
 * React HOC to protect a component
 * Redirects to login page if the user is not authenticated
 * @param {React.Component} Component - The component to wrap and protect
 * @returns {React.Component} - A new component with added authentication check
 */
export const authGuard = (Component) => {
  return (props) => {
    const { isAuthenticated: userIsAuthenticated } = useAuth();

    // Redirect to login if not authenticated
    if (!userIsAuthenticated) {
      if (typeof window !== 'undefined') {
        window.location.href = '/login';
      }
      return null; // Render nothing while redirecting
    }

    return <Component {...props} />;
  };
};

/**
 * Function to handle redirection if user is not authenticated
 * Can be used for client-side navigation
 * @param {string} targetPath - The path to redirect to if not authenticated
 */
export const redirectIfNotAuthenticated = (targetPath = '/login') => {
  if (!isAuthenticated()) {
    if (typeof window !== 'undefined') {
      window.location.href = targetPath;
    }
  }
};

export default {
  isAuthenticated,
  protectRoute,
  fetchCurrentUser,
  authGuard,
  redirectIfNotAuthenticated,
};
