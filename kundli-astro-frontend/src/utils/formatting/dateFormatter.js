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

export default {
    formatDate,
    formatTime,
};
