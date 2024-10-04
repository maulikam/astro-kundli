export const ERROR_MESSAGES = {
  // General errors
  GENERAL_ERROR: "An unexpected error occurred. Please try again later.",
  NETWORK_ERROR: "Unable to connect to the server. Please check your internet connection.",

  // Authentication errors
  INVALID_CREDENTIALS: "Invalid email or password. Please try again.",
  REGISTRATION_FAILED: "Registration failed. Please try again.",
  UNAUTHORIZED: "You are not authorized to perform this action.",

  // User-related errors
  USER_NOT_FOUND: "User not found.",
  USER_UPDATE_FAILED: "Failed to update user information.",
  USER_DELETE_FAILED: "Failed to delete user account.",

  // Kundli-related errors
  KUNDLI_NOT_FOUND: "Kundli not found.",
  KUNDLI_CREATE_FAILED: "Failed to create Kundli.",
  KUNDLI_UPDATE_FAILED: "Failed to update Kundli.",
  KUNDLI_DELETE_FAILED: "Failed to delete Kundli.",

  // Horoscope-related errors
  HOROSCOPE_GENERATION_FAILED: "Failed to generate horoscope.",
  HOROSCOPE_NOT_FOUND: "Horoscope not found.",

  // Matchmaking-related errors
  MATCHMAKING_FAILED: "Failed to perform matchmaking.",
  MATCH_RESULT_NOT_FOUND: "Match result not found.",

  // Report-related errors
  REPORT_GENERATION_FAILED: "Failed to generate report.",
  REPORT_NOT_FOUND: "Report not found.",

  // Chart-related errors
  CHART_GENERATION_FAILED: "Failed to generate chart.",
  CHART_NOT_FOUND: "Chart not found.",

  // City-related errors
  CITY_NOT_FOUND: "City not found.",
  CITY_CREATE_UPDATE_FAILED: "Failed to create or update city.",
  CITY_DELETE_FAILED: "Failed to delete city.",

  // Astrology interpretation errors
  INTERPRETATION_FAILED: "Failed to generate astrological interpretation.",

  // Form validation errors
  REQUIRED_FIELD: "This field is required.",
  INVALID_EMAIL: "Please enter a valid email address.",
  INVALID_DATE: "Please enter a valid date.",
  INVALID_TIME: "Please enter a valid time.",
};

export default ERROR_MESSAGES;
