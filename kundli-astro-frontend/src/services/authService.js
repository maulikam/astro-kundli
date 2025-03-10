import axios from '../lib/axiosInstance';
import { AUTH_API } from '../lib/constants/apiEndpoints';
import { ERROR_MESSAGES } from '../lib/constants/errorMessages';
import { error as logError, info as logInfo } from '../lib/logService';

/**
 * Register a user
 * @param {Object} userData - The user data
 * @returns {Promise<Object>} - The response data
 */
export const register = async (userData) => {
  try {
    logInfo('Attempting to register user');
    const response = await axios.post(AUTH_API.REGISTER, userData);
    logInfo('User registered successfully');
    return response.data;
  } catch (error) {
    logError('Registration failed:', error);
    throw new Error(ERROR_MESSAGES.REGISTRATION_FAILED);
  }
};

/**
 * Authenticate a user
 * @param {Object} credentials - User credentials (email, password)
 * @returns {Promise<Object>} - The response data
 */
export const authenticate = async (credentials) => {
  try {
    logInfo('Attempting to authenticate user');
    const response = await axios.post(AUTH_API.AUTHENTICATE, credentials);
    logInfo('User authenticated successfully');
    return response.data;
  } catch (error) {
    logError('Authentication failed:', error);
    throw new Error(ERROR_MESSAGES.INVALID_CREDENTIALS);
  }
};

/**
 * Fetch the current authenticated user
 * @returns {Promise<Object>} - The user data
 */
export const fetchCurrentUser = async () => {
  try {
    logInfo('Fetching current user data');
    const response = await axios.get(AUTH_API.GET_CURRENT_USER);
    logInfo('Current user data fetched successfully');
    return response.data;
  } catch (error) {
    logError('Failed to fetch current user:', error);
    throw new Error(ERROR_MESSAGES.UNAUTHORIZED);
  }
};

export const authService = {
  register,
  authenticate,
  fetchCurrentUser, // Add the function here
};
