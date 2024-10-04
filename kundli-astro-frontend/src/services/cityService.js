import { get, post, del } from '../lib/api';
import { CITY_API } from '../lib/constants/apiEndpoints';
import { info, error } from '../lib/logService';

/**
 * Get all cities
 */
export const getAllCities = async () => {
  try {
    info('Fetching all cities');
    return await get(CITY_API.GET_ALL_CITIES);
  } catch (err) {
    error('Failed to fetch all cities');
    throw err;
  }
};

/**
 * Create or update a city
 * @param {Object} cityData - City data to create or update
 */
export const createOrUpdateCity = async (cityData) => {
  try {
    info('Creating or updating city');
    return await post(CITY_API.CREATE_OR_UPDATE_CITY, cityData);
  } catch (err) {
    error('Failed to create or update city');
    throw err;
  }
};

/**
 * Get a city by ID
 * @param {string} id - The city ID
 */
export const getCityById = async (id) => {
  try {
    info(`Fetching city with ID: ${id}`);
    return await get(CITY_API.GET_CITY_BY_ID(id));
  } catch (err) {
    error(`Failed to fetch city with ID: ${id}`);
    throw err;
  }
};

/**
 * Delete a city by ID
 * @param {string} id - The city ID
 */
export const deleteCity = async (id) => {
  try {
    info(`Deleting city with ID: ${id}`);
    return await del(CITY_API.DELETE_CITY(id));
  } catch (err) {
    error(`Failed to delete city with ID: ${id}`);
    throw err;
  }
};

/**
 * Get a city by name
 * @param {string} name - The city name
 */
export const getCityByName = async (name) => {
  try {
    info(`Fetching city with name: ${name}`);
    return await get(CITY_API.GET_CITY_BY_NAME(name));
  } catch (err) {
    error(`Failed to fetch city with name: ${name}`);
    throw err;
  }
};

export default {
  getAllCities,
  createOrUpdateCity,
  getCityById,
  deleteCity,
  getCityByName,
};
