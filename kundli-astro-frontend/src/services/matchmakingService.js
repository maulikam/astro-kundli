import axios from '../lib/axiosInstance';
import { MATCHMAKING_API } from '../lib/constants/apiEndpoints';

/**
 * Match two kundlis
 * @param {string} kundli1Id - The first kundli ID
 * @param {string} kundli2Id - The second kundli ID
 */
export const matchKundlis = (kundli1Id, kundli2Id) => axios.post(MATCHMAKING_API.MATCH_KUNDLIS, null, {
  params: { kundli1Id, kundli2Id }
});

/**
 * Get matchmaking result by ID
 * @param {string} id - The matchmaking result ID
 */
export const getMatchMakingResult = (id) => axios.get(MATCHMAKING_API.GET_MATCH_MAKING_RESULT(id));

export default {
  matchKundlis,
  getMatchMakingResult,
};
