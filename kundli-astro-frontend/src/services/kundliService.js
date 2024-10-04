import { get, post, put, del } from '../lib/api';
import { KUNDLI_API } from '../lib/constants/apiEndpoints';
import { error } from '../lib/logService';

/**
 * Get a kundli by ID
 * @param {string} id - The kundli ID
 */
export const getKundliById = async (id) => {
  try {
    return await get(KUNDLI_API.GET_KUNDLI_BY_ID(id));
  } catch (err) {
    error(`Failed to get kundli by ID: ${id}`, err);
    throw err;
  }
};

/**
 * Update a kundli by ID
 * @param {string} id - The kundli ID
 * @param {Object} kundliData - The kundli data to update
 */
export const updateKundli = async (id, kundliData) => {
  try {
    return await put(KUNDLI_API.UPDATE_KUNDLI(id), kundliData);
  } catch (err) {
    error(`Failed to update kundli by ID: ${id}`, err);
    throw err;
  }
};

/**
 * Delete a kundli by ID
 * @param {string} id - The kundli ID
 */
export const deleteKundli = async (id) => {
  try {
    return await del(KUNDLI_API.DELETE_KUNDLI(id));
  } catch (err) {
    error(`Failed to delete kundli by ID: ${id}`, err);
    throw err;
  }
};

/**
 * Create a new kundli
 * @param {Object} kundliData - The kundli data to create
 */
export const createKundli = async (kundliData) => {
  try {
    return await post(KUNDLI_API.CREATE_KUNDLI, kundliData);
  } catch (err) {
    error('Failed to create kundli', err);
    throw err;
  }
};

/**
 * Get all kundlis
 */
export const getAllKundlis = async () => {
  try {
    return await get(KUNDLI_API.GET_ALL_KUNDLIS);
  } catch (err) {
    error('Failed to get all kundlis', err);
    throw err;
  }
};

export default {
  getKundliById,
  updateKundli,
  deleteKundli,
  createKundli,
  getAllKundlis,
};
