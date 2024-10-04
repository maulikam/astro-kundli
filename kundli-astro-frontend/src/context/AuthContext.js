"use client";

import React, { createContext, useState, useContext, useEffect } from 'react';
import { authService } from '../services/authService';
import { useRouter } from 'next/navigation'; // Use next/navigation for App Router compatibility
import { isAuthenticated as checkAuthToken, redirectIfNotAuthenticated } from '../lib/authGuard';
import { info, error as logError } from '../lib/logService'; // Added logging service

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    const initAuth = async () => {
      if (checkAuthToken()) {
        await fetchUserData();
      }
      setIsLoading(false);
    };
    initAuth();
  }, []);

  const fetchUserData = async () => {
    try {
      const userData = await authService.fetchCurrentUser();
      setUser(userData);
      info('User data fetched successfully'); // Log success
    } catch (error) {
      handleAuthError('Error fetching current user:', error); // Log error
    }
  };

  const handleAuthError = (message, error) => {
    logError(message, error); // Log error
    localStorage.removeItem('auth_token');
  };

  const login = async (credentials) => {
    try {
      const { token } = await authService.authenticate(credentials);
      localStorage.setItem('auth_token', token);
      await fetchUserData();
      info('User logged in successfully'); // Log success
      router.push('/dashboard');
    } catch (error) {
      handleAuthError('Login error:', error); // Log error
      throw error;
    }
  };

  const logout = () => {
    localStorage.removeItem('auth_token');
    setUser(null);
    info('User logged out'); // Log logout
    router.push('/login');
  };

  const register = async (userData) => {
    try {
      await authService.register(userData);
      await login(userData); // Automatically log in the user after registration
      info('User registered and logged in successfully'); // Log success
    } catch (error) {
      handleAuthError('Registration error:', error); // Log error
      throw error;
    }
  };

  return (
    <AuthContext.Provider value={{ user, isAuthenticated: !!user, login, logout, register, isLoading }}>
      {!isLoading && children}
    </AuthContext.Provider>
  );
};

export default AuthContext; // Export only the context
