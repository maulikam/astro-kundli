import axios from '../lib/axiosInstance';
import { AUTH_API } from '../lib/constants/apiEndpoints';

/**
 * Register a user
 * @param {Object} userData - The user data
 */
export const register = (userData) => axios.post(AUTH_API.REGISTER, userData);

/**
 * Authenticate a user
 * @param {Object} credentials - User credentials (email, password)
 */
export const authenticate = (credentials) => axios.post(AUTH_API.AUTHENTICATE, credentials);

export default {
  register,
  authenticate,
};
