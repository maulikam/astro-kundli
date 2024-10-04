import axios from '../lib/axiosInstance';
import { MATCHMAKING_API } from '../lib/constants/apiEndpoints';
import { handleError } from '../lib/api'; // Assuming handleError is exported from api.js
import { info, error } from '../lib/logService'; // Importing logging functions

/**
 * Match two kundlis
 * @param {string} kundli1Id - The first kundli ID
 * @param {string} kundli2Id - The second kundli ID
 */
export const matchKundlis = async (kundli1Id, kundli2Id) => {
  try {
    info(`Matching kundlis: ${kundli1Id} and ${kundli2Id}`);
    const response = await axios.post(MATCHMAKING_API.MATCH_KUNDLIS, null, {
      params: { kundli1Id, kundli2Id }
    });
    return response.data;
  } catch (err) {
    error(`Failed to match kundlis: ${kundli1Id} and ${kundli2Id}`);
    handleError(err);
  }
};

/**
 * Get matchmaking result by ID
 * @param {string} id - The matchmaking result ID
 */
export const getMatchMakingResult = async (id) => {
  try {
    info(`Fetching matchmaking result for ID: ${id}`);
    const response = await axios.get(MATCHMAKING_API.GET_MATCH_MAKING_RESULT(id));
    return response.data;
  } catch (err) {
    error(`Failed to fetch matchmaking result for ID: ${id}`);
    handleError(err);
  }
};

export default {
  matchKundlis,
  getMatchMakingResult,
};
