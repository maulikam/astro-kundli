// src/lib/analytics.js

let analyticsProvider = null;

/**
 * Initialize the analytics system with the chosen provider
 * @param {Object} provider - The analytics provider object (e.g., Google Analytics)
 */
export function initializeAnalytics(provider) {
  if (!provider) {
    console.warn("No analytics provider supplied. Analytics initialization aborted.");
    return;
  }
  analyticsProvider = provider;
  console.log('Analytics initialized successfully');
}

/**
 * Track a page view
 * @param {string} pageName - The name or path of the page being viewed
 */
export function trackPageView(pageName) {
  if (!analyticsProvider) {
    console.warn('Analytics provider not initialized. Page view tracking aborted.');
    return;
  }

  if (!pageName) {
    console.warn("Page name not provided for tracking page view.");
    return;
  }

  console.log(`Tracking page view: ${pageName}`);
  // Implement the actual tracking logic here, e.g.:
  // analyticsProvider.trackPageView(pageName);
}

/**
 * Track a custom event
 * @param {string} category - The event category
 * @param {string} action - The event action
 * @param {string} [label] - Optional event label
 * @param {number} [value] - Optional event value
 */
export function trackEvent(category, action, label = null, value = null) {
  if (!analyticsProvider) {
    console.warn('Analytics provider not initialized. Event tracking aborted.');
    return;
  }

  if (!category || !action) {
    console.warn('Event category and action are required for tracking.');
    return;
  }

  console.log(`Tracking event: ${category} - ${action}${label ? ` - ${label}` : ''}${value !== null ? ` - ${value}` : ''}`);
  // Implement the actual tracking logic here, e.g.:
  // analyticsProvider.trackEvent(category, action, label, value);
}

export default {
  initializeAnalytics,
  trackPageView,
  trackEvent,
};
