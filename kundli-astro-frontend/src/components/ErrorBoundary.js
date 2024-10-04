// src/components/ErrorBoundary.js

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Error from './Error';
import { error as logError } from '../lib/logService';

class ErrorBoundary extends Component {
  state = {
    hasError: false,
    errorMessage: '',
  };

  static getDerivedStateFromError(error) {
    return { hasError: true, errorMessage: error.message };
  }

  componentDidCatch(error, errorInfo) {
    logError('Error caught by ErrorBoundary:', error, errorInfo);
  }

  handleRetry = () => {
    this.setState({ hasError: false, errorMessage: '' });
  };

  render() {
    const { hasError, errorMessage } = this.state;
    const { children } = this.props;

    if (hasError) {
      return <Error message={errorMessage} retryCallback={this.handleRetry} />;
    }

    return children;
  }
}

ErrorBoundary.propTypes = {
  children: PropTypes.node.isRequired,
};

export default ErrorBoundary;
