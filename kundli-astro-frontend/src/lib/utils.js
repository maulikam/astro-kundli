// src/lib/utils.js

/**
 * Formats a given date into a readable string format
 * @param {Date|string} date - The date to format
 * @param {Object} options - Formatting options for date (optional)
 * @returns {string} - Formatted date string
 */
export const formatDate = (date, options = { year: 'numeric', month: 'long', day: 'numeric' }) => {
    if (!date) return '';
    return new Date(date).toLocaleDateString(undefined, options);
  };
  
  /**
   * Formats time in a given locale (e.g., for birth time representation)
   * @param {Date|string} time - The time to format
   * @returns {string} - Formatted time string (e.g., 10:30 AM)
   */
  export const formatTime = (time) => {
    if (!time) return '';
    const options = { hour: 'numeric', minute: 'numeric', hour12: true };
    return new Date(time).toLocaleTimeString(undefined, options);
  };
  
  /**
   * Deep clones a JavaScript object
   * @param {Object} obj - The object to clone
   * @returns {Object} - A deep clone of the original object
   */
  export const deepClone = (obj) => {
    if (!obj) return null;
    return JSON.parse(JSON.stringify(obj));
  };
  
  /**
   * Validates if a given string is a valid email address
   * @param {string} email - The email to validate
   * @returns {boolean} - True if valid, false otherwise
   */
  export const isValidEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };
  
  /**
   * Capitalizes the first letter of each word in a string
   * @param {string} str - The string to capitalize
   * @returns {string} - Capitalized string
   */
  export const capitalizeWords = (str) => {
    if (!str) return '';
    return str.replace(/\b\w/g, (char) => char.toUpperCase());
  };
  
  /**
   * Formats the name to be used in astrology charts (e.g., ensuring title case)
   * @param {string} name - The name to format
   * @returns {string} - Formatted name with title case
   */
  export const formatAstroName = (name) => {
    return capitalizeWords(name.trim());
  };
  
  /**
   * Converts degrees to radians (useful in astrology calculations)
   * @param {number} degrees - The value in degrees
   * @returns {number} - The value in radians
   */
  export const degreesToRadians = (degrees) => {
    return (degrees * Math.PI) / 180;
  };
  
  /**
   * Converts radians to degrees (useful in astrology calculations)
   * @param {number} radians - The value in radians
   * @returns {number} - The value in degrees
   */
  export const radiansToDegrees = (radians) => {
    return (radians * 180) / Math.PI;
  };
  
  /**
   * Generates a unique ID (e.g., for chart or kundli identification)
   * @returns {string} - A unique identifier string
   */
  export const generateUniqueId = () => {
    return '_' + Math.random().toString(36).substr(2, 9);
  };
  
  export default {
    formatDate,
    formatTime,
    deepClone,
    isValidEmail,
    capitalizeWords,
    formatAstroName,
    degreesToRadians,
    radiansToDegrees,
    generateUniqueId,
  };
