// src/lib/accessibility.js

/**
 * Focuses on the first focusable element within a container
 * @param {HTMLElement} container - The container element to search within
 */
export function focusFirstElement(container) {
  const focusableElements = container.querySelectorAll(
    'button, [href], input, select, textarea, [tabindex]:not([tabindex="-1"])'
  );
  if (focusableElements.length) {
    focusableElements[0].focus();
  }
}

/**
 * Traps focus within a specified container
 * @param {HTMLElement} container - The container to trap focus within
 */
export function trapFocus(container) {
  const focusableElements = container.querySelectorAll(
    'button, [href], input, select, textarea, [tabindex]:not([tabindex="-1"])'
  );
  const firstElement = focusableElements[0];
  const lastElement = focusableElements[focusableElements.length - 1];

  container.addEventListener('keydown', (e) => {
    if (e.key === 'Tab') {
      if (e.shiftKey && document.activeElement === firstElement) {
        e.preventDefault();
        lastElement.focus();
      } else if (!e.shiftKey && document.activeElement === lastElement) {
        e.preventDefault();
        firstElement.focus();
      }
    }
  });
}

/**
 * Announces a message to screen readers
 * @param {string} message - The message to be announced
 */
export function announceToScreenReader(message) {
  const announcer = document.getElementById('sr-announcer');
  if (announcer) {
    announcer.textContent = message;
  } else {
    console.warn('Screen reader announcer element not found');
  }
}

/**
 * Sets the page title, including any necessary prefix or suffix
 * @param {string} title - The main title of the page
 */
export function setPageTitle(title) {
  document.title = `${title} | Astro-Kundli App`;
}

/**
 * Adds aria-label to elements that need additional context for screen readers
 * @param {string} selector - The CSS selector for the elements to update
 * @param {string} label - The aria-label to apply
 */
export function addAriaLabel(selector, label) {
  const elements = document.querySelectorAll(selector);
  elements.forEach((element) => {
    element.setAttribute('aria-label', label);
  });
}

export default {
  focusFirstElement,
  trapFocus,
  announceToScreenReader,
  setPageTitle,
  addAriaLabel,
};