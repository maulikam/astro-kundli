// src/components/ui/InputField.js

import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

/**
 * A reusable input field component for text, password, etc.
 * @param {Object} props - The properties passed to the InputField component.
 */
const InputField = ({
  label,
  type = 'text',
  name,
  value,
  onChange,
  placeholder = '',
  className,
  disabled = false,
  required = false,
  error = '',
  autoComplete = 'off',
}) => {
  const inputClass = classNames('input-field', className, {
    'input-error': error,
  });

  return (
    <div className="input-wrapper">
      {label && (
        <label htmlFor={name} className="input-label">
          {label}
        </label>
      )}
      <input
        type={type}
        id={name}
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        className={inputClass}
        disabled={disabled}
        required={required}
        autoComplete={autoComplete}
      />
      {error && <span className="error-message">{error}</span>}
    </div>
  );
};

InputField.propTypes = {
  label: PropTypes.string,
  type: PropTypes.oneOf(['text', 'password', 'email', 'number', 'tel', 'url']),
  name: PropTypes.string.isRequired,
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  onChange: PropTypes.func.isRequired,
  placeholder: PropTypes.string,
  className: PropTypes.string,
  disabled: PropTypes.bool,
  required: PropTypes.bool,
  error: PropTypes.string,
  autoComplete: PropTypes.string,
};

export default InputField;
