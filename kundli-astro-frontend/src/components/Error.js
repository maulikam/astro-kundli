// src/components/Error.js

import React, { useCallback } from 'react';
import PropTypes from 'prop-types';

const Error = ({ message = 'An unexpected error occurred. Please try again.', retryCallback }) => {
  const handleRetry = useCallback(() => {
    if (retryCallback) {
      retryCallback();
    }
  }, [retryCallback]);

  return (
    <div className="error-container">
      <p className="error-message">{message}</p>
      {retryCallback && (
        <button onClick={handleRetry} className="retry-button">
          Retry
        </button>
      )}
    </div>
  );
};

Error.propTypes = {
  message: PropTypes.string,
  retryCallback: PropTypes.func,
};

export default Error;
