/**
 * Logs a message to the console with a specific log level
 * @param {string} level - The log level (e.g., 'info', 'warn', 'error')
 * @param {string} message - The message to log
 */
function log(level, message) {
  const timestamp = new Date().toISOString();
  console[level](`[${timestamp}] ${message}`);
}

/**
 * Logs an informational message
 * @param {string} message - The message to log
 */
export function info(message) {
  log('info', message);
}

/**
 * Logs a warning message
 * @param {string} message - The message to log
 */
export function warn(message) {
  log('warn', message);
}

/**
 * Logs an error message
 * @param {string} message - The message to log
 */
export function error(message) {
  log('error', message);
}

export default {
  info,
  warn,
  error,
};
