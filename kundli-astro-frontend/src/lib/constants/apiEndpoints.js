// src/lib/constants/apiEndpoints.js

const BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8080"; // Fallback if BASE_URL is not defined

// User API Endpoints
export const USERS_API = {
  GET_USER_BY_ID: (id) => `${BASE_URL}/api/users/${id}`,
  UPDATE_USER: (id) => `${BASE_URL}/api/users/${id}`,
  DELETE_USER: (id) => `${BASE_URL}/api/users/${id}`,
  REGISTER_USER: `${BASE_URL}/api/users/register`,
  GET_ALL_USERS: `${BASE_URL}/api/users`,
  GET_USER_BY_EMAIL: (email) => `${BASE_URL}/api/users/email/${email}`,
};

// Kundli API Endpoints
export const KUNDLI_API = {
  GET_KUNDLI_BY_ID: (id) => `${BASE_URL}/api/kundlis/${id}`,
  UPDATE_KUNDLI: (id) => `${BASE_URL}/api/kundlis/${id}`,
  DELETE_KUNDLI: (id) => `${BASE_URL}/api/kundlis/${id}`,
  CREATE_KUNDLI: `${BASE_URL}/api/kundlis`,
  GET_ALL_KUNDLIS: `${BASE_URL}/api/kundlis`,
};

// Horoscope API Endpoints
export const HOROSCOPE_API = {
  GENERATE_YEARLY_HOROSCOPE: (kundliId) => `${BASE_URL}/api/horoscope/yearly/${kundliId}`,
  GENERATE_MONTHLY_HOROSCOPE: (kundliId) => `${BASE_URL}/api/horoscope/monthly/${kundliId}`,
  GENERATE_DAILY_HOROSCOPE: (kundliId) => `${BASE_URL}/api/horoscope/daily/${kundliId}`,
  GET_HOROSCOPE_FOR_KUNDLI: (kundliId) => `${BASE_URL}/api/horoscope/kundli/${kundliId}`,
};

// Matchmaking API Endpoints
export const MATCHMAKING_API = {
  MATCH_KUNDLIS: `${BASE_URL}/api/matchmaking/match`,
  GET_MATCH_MAKING_RESULT: (id) => `${BASE_URL}/api/matchmaking/${id}`,
};

// Reports API Endpoints
export const REPORTS_API = {
  GENERATE_REPORT: (kundliId) => `${BASE_URL}/api/reports/generate/${kundliId}`,
  GET_REPORT: (reportId) => `${BASE_URL}/api/reports/${reportId}`,
};

// Charts API Endpoints
export const CHARTS_API = {
  GENERATE_CHART: `${BASE_URL}/api/charts/generate`,
  GET_CHART_BY_ID: (chartId) => `${BASE_URL}/api/charts/${chartId}`,
  GET_CHARTS_BY_KUNDLI_ID: (kundliId) => `${BASE_URL}/api/charts/kundli/${kundliId}`,
};

// City API Endpoints
export const CITY_API = {
  GET_ALL_CITIES: `${BASE_URL}/api/cities`,
  CREATE_OR_UPDATE_CITY: `${BASE_URL}/api/cities`,
  GET_CITY_BY_ID: (id) => `${BASE_URL}/api/cities/${id}`,
  DELETE_CITY: (id) => `${BASE_URL}/api/cities/${id}`,
  GET_CITY_BY_NAME: (name) => `${BASE_URL}/api/cities/name/${name}`,
};

// Authentication API Endpoints
export const AUTH_API = {
  REGISTER: `${BASE_URL}/api/auth/register`,
  AUTHENTICATE: `${BASE_URL}/api/auth/authenticate`,
};

// Astrology Interpretation API Endpoints
export const ASTROLOGY_API = {
  TRANSIT_INTERPRETATION: `${BASE_URL}/api/astrology/interpretation/transits`,
  SYNASTRY_INTERPRETATION: `${BASE_URL}/api/astrology/interpretation/synastry`,
  NATAL_INTERPRETATION: `${BASE_URL}/api/astrology/interpretation/natal`,
};

export default {
  USERS_API,
  KUNDLI_API,
  HOROSCOPE_API,
  MATCHMAKING_API,
  REPORTS_API,
  CHARTS_API,
  CITY_API,
  AUTH_API,
  ASTROLOGY_API,
};
