import axios from '../lib/axiosInstance';
import { USERS_API } from '../lib/constants/apiEndpoints';
import { handleError } from '../lib/api'; // Assuming handleError is exported from api.js
import { info, error } from '../lib/logService'; // Importing log functions

/**
 * Get a user by ID
 * @param {string} id - The user ID
 */
export const getUserById = async (id) => {
  try {
    info(`Fetching user with ID: ${id}`);
    const response = await axios.get(USERS_API.GET_USER_BY_ID(id));
    return response.data;
  } catch (err) {
    error(`Failed to fetch user with ID: ${id}`);
    handleError(err);
  }
};

/**
 * Update a user by ID
 * @param {string} id - The user ID
 * @param {Object} userData - The user data to update
 */
export const updateUser = async (id, userData) => {
  try {
    info(`Updating user with ID: ${id}`);
    const response = await axios.put(USERS_API.UPDATE_USER(id), userData);
    return response.data;
  } catch (err) {
    error(`Failed to update user with ID: ${id}`);
    handleError(err);
  }
};

/**
 * Delete a user by ID
 * @param {string} id - The user ID
 */
export const deleteUser = async (id) => {
  try {
    info(`Deleting user with ID: ${id}`);
    const response = await axios.delete(USERS_API.DELETE_USER(id));
    return response.data;
  } catch (err) {
    error(`Failed to delete user with ID: ${id}`);
    handleError(err);
  }
};

/**
 * Register a new user
 * @param {Object} userData - The user data to register
 */
export const registerUser = async (userData) => {
  try {
    info('Registering new user');
    const response = await axios.post(USERS_API.REGISTER_USER, userData);
    return response.data;
  } catch (err) {
    error('Failed to register new user');
    handleError(err);
  }
};

/**
 * Get all users
 */
export const getAllUsers = async () => {
  try {
    info('Fetching all users');
    const response = await axios.get(USERS_API.GET_ALL_USERS);
    return response.data;
  } catch (err) {
    error('Failed to fetch all users');
    handleError(err);
  }
};

/**
 * Get user by email
 * @param {string} email - The user's email
 */
export const getUserByEmail = async (email) => {
  try {
    info(`Fetching user with email: ${email}`);
    const response = await axios.get(USERS_API.GET_USER_BY_EMAIL(email));
    return response.data;
  } catch (err) {
    error(`Failed to fetch user with email: ${email}`);
    handleError(err);
  }
};

export default {
  getUserById,
  updateUser,
  deleteUser,
  registerUser,
  getAllUsers,
  getUserByEmail,
};
