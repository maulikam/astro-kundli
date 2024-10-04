import axios from '../lib/axiosInstance';
import { REPORTS_API } from '../lib/constants/apiEndpoints';

/**
 * Generate a report for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const generateReport = (kundliId) => axios.post(REPORTS_API.GENERATE_REPORT(kundliId));

/**
 * Get a report by ID
 * @param {string} reportId - The report ID
 */
export const getReport = (reportId) => axios.get(REPORTS_API.GET_REPORT(reportId));

export default {
  generateReport,
  getReport,
};
