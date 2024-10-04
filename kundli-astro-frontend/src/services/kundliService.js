import axios from '../lib/axiosInstance';
import { KUNDLI_API } from '../lib/constants/apiEndpoints';

/**
 * Get a kundli by ID
 * @param {string} id - The kundli ID
 */
export const getKundliById = (id) => axios.get(KUNDLI_API.GET_KUNDLI_BY_ID(id));

/**
 * Update a kundli by ID
 * @param {string} id - The kundli ID
 * @param {Object} kundliData - The kundli data to update
 */
export const updateKundli = (id, kundliData) => axios.put(KUNDLI_API.UPDATE_KUNDLI(id), kundliData);

/**
 * Delete a kundli by ID
 * @param {string} id - The kundli ID
 */
export const deleteKundli = (id) => axios.delete(KUNDLI_API.DELETE_KUNDLI(id));

/**
 * Create a new kundli
 * @param {Object} kundliData - The kundli data to create
 */
export const createKundli = (kundliData) => axios.post(KUNDLI_API.CREATE_KUNDLI, kundliData);

/**
 * Get all kundlis
 */
export const getAllKundlis = () => axios.get(KUNDLI_API.GET_ALL_KUNDLIS);

export default {
  getKundliById,
  updateKundli,
  deleteKundli,
  createKundli,
  getAllKundlis,
};
