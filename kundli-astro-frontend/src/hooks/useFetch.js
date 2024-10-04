import { useState, useEffect, useCallback } from 'react';
import axiosInstance from '../lib/axiosInstance';

const useFetch = (url, options = {}, autoFetch = true) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  // Use useCallback to memoize fetchData function
  const fetchData = useCallback(async () => {
    setIsLoading(true);
    try {
      const response = await axiosInstance.get(url, options);
      setData(response.data);
      setError(null);
    } catch (err) {
      setError(err);
    } finally {
      setIsLoading(false);
    }
  }, [url, options]); // Add options to dependencies

  useEffect(() => {
    if (autoFetch) {
      fetchData();
    }
  }, [fetchData, autoFetch]); // Use fetchData in dependencies

  return { data, error, isLoading, refetch: fetchData };
};

export default useFetch;
