import axios from '../lib/axiosInstance';
import { USERS_API } from '../lib/constants/apiEndpoints';

/**
 * Get a user by ID
 * @param {string} id - The user ID
 */
export const getUserById = (id) => axios.get(USERS_API.GET_USER_BY_ID(id));

/**
 * Update a user by ID
 * @param {string} id - The user ID
 * @param {Object} userData - The user data to update
 */
export const updateUser = (id, userData) => axios.put(USERS_API.UPDATE_USER(id), userData);

/**
 * Delete a user by ID
 * @param {string} id - The user ID
 */
export const deleteUser = (id) => axios.delete(USERS_API.DELETE_USER(id));

/**
 * Register a new user
 * @param {Object} userData - The user data to register
 */
export const registerUser = (userData) => axios.post(USERS_API.REGISTER_USER, userData);

/**
 * Get all users
 */
export const getAllUsers = () => axios.get(USERS_API.GET_ALL_USERS);

/**
 * Get user by email
 * @param {string} email - The user's email
 */
export const getUserByEmail = (email) => axios.get(USERS_API.GET_USER_BY_EMAIL(email));

export default {
  getUserById,
  updateUser,
  deleteUser,
  registerUser,
  getAllUsers,
  getUserByEmail,
};
