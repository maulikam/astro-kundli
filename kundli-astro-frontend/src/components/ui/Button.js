// src/components/ui/Button.js

import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

/**
 * A reusable button component for consistent styles and behavior.
 * @param {Object} props - The properties passed to the Button component.
 */
const Button = ({ type = 'button', onClick, children, className, disabled = false, variant = 'primary', size = 'medium' }) => {
  const buttonClass = getButtonClassNames(variant, size, className);

  return (
    <button type={type} onClick={onClick} className={buttonClass} disabled={disabled}>
      {children}
    </button>
  );
};

/**
 * Generates the class names for the button based on its variant and size.
 * @param {string} variant - The variant of the button (e.g., 'primary', 'secondary').
 * @param {string} size - The size of the button (e.g., 'small', 'medium', 'large').
 * @param {string} className - Additional class names to apply.
 * @returns {string} - The complete class name string for the button.
 */
const getButtonClassNames = (variant, size, className) => {
  return classNames(
    'button',
    {
      'button-primary': variant === 'primary',
      'button-secondary': variant === 'secondary',
      'button-danger': variant === 'danger',
      'button-small': size === 'small',
      'button-medium': size === 'medium',
      'button-large': size === 'large',
    },
    className
  );
};

Button.propTypes = {
  type: PropTypes.oneOf(['button', 'submit', 'reset']),
  onClick: PropTypes.func,
  children: PropTypes.node.isRequired,
  className: PropTypes.string,
  disabled: PropTypes.bool,
  variant: PropTypes.oneOf(['primary', 'secondary', 'danger']),
  size: PropTypes.oneOf(['small', 'medium', 'large']),
};

export default Button;
