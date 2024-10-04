import axios from './axiosInstance';
import { ERROR_MESSAGES } from './constants/errorMessages';

const handleError = (error) => {
  if (error.response) {
    // The request was made and the server responded with a status code
    // that falls out of the range of 2xx
    const errorMessage = error.response.data.message || ERROR_MESSAGES.GENERAL_ERROR;
    throw new Error(errorMessage);
  } else if (error.request) {
    // The request was made but no response was received
    throw new Error(ERROR_MESSAGES.NETWORK_ERROR);
  } else {
    // Something happened in setting up the request that triggered an Error
    throw new Error(ERROR_MESSAGES.GENERAL_ERROR);
  }
};

export const get = async (url, config = {}) => {
  try {
    const response = await axios.get(url, config);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const post = async (url, data, config = {}) => {
  try {
    const response = await axios.post(url, data, config);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const put = async (url, data, config = {}) => {
  try {
    const response = await axios.put(url, data, config);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const del = async (url, config = {}) => {
  try {
    const response = await axios.delete(url, config);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export default {
  get,
  post,
  put,
  del,
};
