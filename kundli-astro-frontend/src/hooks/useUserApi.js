import { useState } from 'react';
import { userService } from '../services/userService';
import { error as logError, info as logInfo } from '../lib/logService';

const useUserApi = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [user, setUser] = useState(null);

  const handleApiCall = async (apiCall, successMessage, errorMessage) => {
    setIsLoading(true);
    try {
      const result = await apiCall();
      logInfo(successMessage);
      return result;
    } catch (error) {
      logError(errorMessage, error);
      throw error;
    } finally {
      setIsLoading(false);
    }
  };

  const getUserById = async (id) => {
    logInfo(`Fetching user with id: ${id}`);
    const userData = await handleApiCall(
      () => userService.getUserById(id),
      'User fetched successfully',
      'Failed to fetch user:'
    );
    setUser(userData);
    return userData;
  };

  const updateUser = async (id, userData) => {
    logInfo(`Updating user with id: ${id}`);
    const updatedUser = await handleApiCall(
      () => userService.updateUser(id, userData),
      'User updated successfully',
      'Failed to update user:'
    );
    setUser(updatedUser);
    return updatedUser;
  };

  return {
    user,
    getUserById,
    updateUser,
    isLoading,
  };
};

export default useUserApi;
