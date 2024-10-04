import axios from '../lib/axiosInstance';
import { CITY_API } from '../lib/constants/apiEndpoints';

/**
 * Get all cities
 */
export const getAllCities = () => axios.get(CITY_API.GET_ALL_CITIES);

/**
 * Create or update a city
 * @param {Object} cityData - City data to create or update
 */
export const createOrUpdateCity = (cityData) => axios.post(CITY_API.CREATE_OR_UPDATE_CITY, cityData);

/**
 * Get a city by ID
 * @param {string} id - The city ID
 */
export const getCityById = (id) => axios.get(CITY_API.GET_CITY_BY_ID(id));

/**
 * Delete a city by ID
 * @param {string} id - The city ID
 */
export const deleteCity = (id) => axios.delete(CITY_API.DELETE_CITY(id));

/**
 * Get a city by name
 * @param {string} name - The city name
 */
export const getCityByName = (name) => axios.get(CITY_API.GET_CITY_BY_NAME(name));

export default {
  getAllCities,
  createOrUpdateCity,
  getCityById,
  deleteCity,
  getCityByName,
};
