// src/lib/serviceWorker.js

/**
 * Registers a service worker for the application
 */
export const registerServiceWorker = () => {
    if ('serviceWorker' in navigator) {
      // Only register the service worker in production
      if (process.env.NODE_ENV === 'production') {
        window.addEventListener('load', () => {
          navigator.serviceWorker
            .register('/service-worker.js')
            .then((registration) => {
              console.log('ServiceWorker registered successfully:', registration);
            })
            .catch((error) => {
              console.error('ServiceWorker registration failed:', error);
            });
        });
      } else {
        console.info('ServiceWorker not registered in development mode.');
      }
    } else {
      console.warn('ServiceWorker not supported by the browser.');
    }
  };
  
  /**
   * Unregisters any registered service worker
   * (useful in development or if you want to reset service worker)
   */
  export const unregisterServiceWorker = () => {
    if ('serviceWorker' in navigator) {
      navigator.serviceWorker.getRegistrations().then((registrations) => {
        registrations.forEach((registration) => {
          registration.unregister();
          console.info('ServiceWorker unregistered:', registration);
        });
      });
    } else {
      console.warn('ServiceWorker not supported by the browser.');
    }
  };
  
  export default {
    registerServiceWorker,
    unregisterServiceWorker,
  };
