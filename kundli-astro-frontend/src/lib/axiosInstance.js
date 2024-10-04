// src/lib/axiosInstance.js

import axios from 'axios';
import { ERROR_MESSAGES } from './constants/errorMessages';
import { AUTH_API } from './constants/apiEndpoints';

const BASE_URL = AUTH_API.BASE_URL; 

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
  timeout: 10000,
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
    console.log(`[Request] ${config.method.toUpperCase()}: ${config.url}`, config);
    return config;
  },
  (error) => {
    console.error('[Request Error]:', error.message);
    return Promise.reject(new Error(ERROR_MESSAGES.GENERAL_ERROR));
  }
);

// Response Interceptor
axiosInstance.interceptors.response.use(
  (response) => {
    console.log(`[Response] ${response.status}: ${response.config.url}`, response);
    return response;
  },
  (error) => {
    // Handle different types of errors and log for better debugging
    if (!error.response) {
      console.error('[Network Error]:', error.message);
      return Promise.reject(new Error(ERROR_MESSAGES.NETWORK_ERROR));
    }

    const { response } = error;
    const { status } = response;

    switch (status) {
      case 400:
        console.error('[Bad Request]:', response.data);
        return Promise.reject(new Error(response.data.message || 'Bad request. Please check your input.'));
      case 401:
        console.error('[Unauthorized]:', response.data);
        return Promise.reject(new Error(ERROR_MESSAGES.UNAUTHORIZED));
      case 403:
        console.error('[Forbidden]:', response.data);
        return Promise.reject(new Error('Access forbidden. You do not have the necessary permissions.'));
      case 404:
        console.error('[Not Found]:', response.data);
        return Promise.reject(new Error(response.data.message || 'Resource not found.'));
      case 500:
        console.error('[Internal Server Error]:', response.data);
        return Promise.reject(new Error('Internal server error. Please try again later.'));
      default:
        console.error('[Unhandled Error]:', response.data);
        return Promise.reject(new Error(response.data.message || ERROR_MESSAGES.GENERAL_ERROR));
    }
  }
);

export default axiosInstance;
