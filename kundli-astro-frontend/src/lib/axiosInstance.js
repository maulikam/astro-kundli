// src/lib/axiosInstance.js

import axios from 'axios';
import { ERROR_MESSAGES } from './constants/errorMessages';
import { AUTH_API } from './constants/apiEndpoints';

// Extract the base URL for better readability and flexibility
const BASE_URL = AUTH_API.BASE_URL || 'http://localhost:8080'; // Fallback if AUTH_API.BASE_URL is not defined

/**
 * Utility to get the auth token from localStorage
 * @returns {string|null} - The token string or null if not available
 */
const getAuthToken = () => {
  return localStorage.getItem('auth_token');
};

// Create an Axios instance with default settings
const axiosInstance = axios.create({
  baseURL: BASE_URL,
  timeout: 10000, // Set a timeout for requests (in milliseconds)
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request Interceptor
axiosInstance.interceptors.request.use(
  (config) => {
    // Attach Authorization token if available
    const token = getAuthToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    // Optional: Log outgoing request for debugging
    console.log('Request:', config);
    return config;
  },
  (error) => {
    // Log and handle request error before sending the request
    console.error('Request error:', error);
    return Promise.reject(new Error(ERROR_MESSAGES.GENERAL_ERROR));
  }
);

// Response Interceptor
axiosInstance.interceptors.response.use(
  (response) => {
    // Log response for debugging
    console.log('Response:', response);
    return response;
  },
  (error) => {
    // Enhanced error handling for different error types
    if (!error.response) {
      // Network error (e.g., server is unreachable)
      console.error('Network error:', error);
      return Promise.reject(new Error(ERROR_MESSAGES.NETWORK_ERROR));
    }

    const { response } = error;
    const { status } = response;

    // Handle specific HTTP status codes
    switch (status) {
      case 400:
        console.error('Bad request:', response.data);
        return Promise.reject(new Error(response.data.message || 'Bad request. Please check your input.'));
      case 401:
        console.error('Unauthorized:', response.data);
        return Promise.reject(new Error(ERROR_MESSAGES.UNAUTHORIZED));
      case 403:
        console.error('Forbidden:', response.data);
        return Promise.reject(new Error('Access forbidden. You do not have the necessary permissions.'));
      case 404:
        console.error('Not found:', response.data);
        return Promise.reject(new Error(response.data.message || 'Resource not found.'));
      case 500:
        console.error('Internal server error:', response.data);
        return Promise.reject(new Error('Internal server error. Please try again later.'));
      default:
        // Handle all other status codes
        console.error('Unhandled error:', response.data);
        return Promise.reject(new Error(response.data.message || ERROR_MESSAGES.GENERAL_ERROR));
    }
  }
);

export default axiosInstance;
