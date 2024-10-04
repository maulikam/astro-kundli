import axios from '../lib/axiosInstance';
import { CHARTS_API } from '../lib/constants/apiEndpoints';
import { error } from '../lib/logService'; // Importing the error logging function

/**
 * Generate a chart for a kundli
 * @param {Object} params - Parameters including kundli ID and chart type
 */
export const generateChart = async (params) => {
  try {
    return await axios.post(CHARTS_API.GENERATE_CHART, null, { params });
  } catch (err) {
    error(`Failed to generate chart: ${err.message}`);
    throw err;
  }
};

/**
 * Get a chart by ID
 * @param {string} chartId - The chart ID
 */
export const getChartById = async (chartId) => {
  try {
    return await axios.get(CHARTS_API.GET_CHART_BY_ID(chartId));
  } catch (err) {
    error(`Failed to get chart by ID: ${err.message}`);
    throw err;
  }
};

/**
 * Get all charts for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const getChartsByKundliId = async (kundliId) => {
  try {
    return await axios.get(CHARTS_API.GET_CHARTS_BY_KUNDLI_ID(kundliId));
  } catch (err) {
    error(`Failed to get charts by kundli ID: ${err.message}`);
    throw err;
  }
};

export default {
  generateChart,
  getChartById,
  getChartsByKundliId,
};
