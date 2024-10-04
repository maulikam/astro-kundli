import { post, get } from '../lib/api';
import { HOROSCOPE_API } from '../lib/constants/apiEndpoints';

/**
 * Generate a yearly horoscope for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const generateYearlyHoroscope = (kundliId) => post(HOROSCOPE_API.GENERATE_YEARLY_HOROSCOPE(kundliId));

/**
 * Generate a monthly horoscope for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const generateMonthlyHoroscope = (kundliId) => post(HOROSCOPE_API.GENERATE_MONTHLY_HOROSCOPE(kundliId));

/**
 * Generate a daily horoscope for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const generateDailyHoroscope = (kundliId) => post(HOROSCOPE_API.GENERATE_DAILY_HOROSCOPE(kundliId));

/**
 * Get horoscope for a kundli
 * @param {string} kundliId - The kundli ID
 */
export const getHoroscopeForKundli = (kundliId) => get(HOROSCOPE_API.GET_HOROSCOPE_FOR_KUNDLI(kundliId));

export default {
  generateYearlyHoroscope,
  generateMonthlyHoroscope,
  generateDailyHoroscope,
  getHoroscopeForKundli,
};
