import axios from '../lib/axiosInstance';
import { CHARTS_API } from '../lib/constants/apiEndpoints';

/**
 * Generate a chart for a kundli
 * @param {Object} params - Parameters including kundli ID and chart type
 */
export const generateChart = (params) => axios.post(CHARTS_API.GENERATE_CHART, null, { params });

/**
 * Get a chart by ID
 * @param {string} chartId - The chart ID
 */
export const getChartById = (chartId) => axios.get(CHARTS_API.GET_CHART_BY_ID(chartId));

/**
 * Get all charts for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const getChartsByKundliId = (kundliId) => axios.get(CHARTS_API.GET_CHARTS_BY_KUNDLI_ID(kundliId));

export default {
  generateChart,
  getChartById,
  getChartsByKundliId,
};
