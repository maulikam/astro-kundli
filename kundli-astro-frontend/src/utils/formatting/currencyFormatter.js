/**
 * Formats a number into a currency string.
 * @param {number} amount - The amount to format.
 * @param {string} currency - The currency code (e.g., 'USD', 'EUR').
 * @param {string} [locale='en-US'] - The locale to use for formatting (default is 'en-US').
 * @returns {string} - The formatted currency string.
 */
export const currencyFormatter = (amount, currency, locale = 'en-US') => {
  if (isNaN(amount)) {
    throw new Error('Invalid amount: must be a number');
  }

  return new Intl.NumberFormat(locale, {
    style: 'currency',
    currency: currency,
  }).format(amount);
};

export default currencyFormatter;
