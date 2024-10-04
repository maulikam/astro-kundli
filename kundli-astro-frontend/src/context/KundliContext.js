import React, { createContext, useState, useContext, useEffect } from 'react';
import { kundliService } from '../services/kundliService';
import { error as logError, info as logInfo } from '../lib/logService';

const KundliContext = createContext();

export const KundliProvider = ({ children }) => {
  const [kundlis, setKundlis] = useState([]);
  const [selectedKundli, setSelectedKundli] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const fetchKundlis = async () => {
      setIsLoading(true);
      try {
        logInfo('Fetching all kundlis');
        const kundliData = await kundliService.getAllKundlis();
        setKundlis(kundliData);
        logInfo('Kundlis fetched successfully');
      } catch (error) {
        logError('Failed to fetch kundlis:', error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchKundlis();
  }, []);

  const createKundli = async (kundliData) => {
    try {
      logInfo('Creating a new kundli');
      const newKundli = await kundliService.createKundli(kundliData);
      setKundlis((prevKundlis) => [...prevKundlis, newKundli]);
      logInfo('Kundli created successfully');
    } catch (error) {
      logError('Failed to create kundli:', error);
      throw error;
    }
  };

  const updateKundli = async (id, updatedData) => {
    try {
      logInfo(`Updating kundli with id: ${id}`);
      const updatedKundli = await kundliService.updateKundli(id, updatedData);
      setKundlis((prevKundlis) =>
        prevKundlis.map((kundli) => (kundli.id === id ? updatedKundli : kundli))
      );
      logInfo('Kundli updated successfully');
    } catch (error) {
      logError('Failed to update kundli:', error);
      throw error;
    }
  };

  const deleteKundli = async (id) => {
    try {
      logInfo(`Deleting kundli with id: ${id}`);
      await kundliService.deleteKundli(id);
      setKundlis((prevKundlis) => prevKundlis.filter((kundli) => kundli.id !== id));
      logInfo('Kundli deleted successfully');
    } catch (error) {
      logError('Failed to delete kundli:', error);
      throw error;
    }
  };

  return (
    <KundliContext.Provider value={{ kundlis, selectedKundli, setSelectedKundli, createKundli, updateKundli, deleteKundli, isLoading }}>
      {children}
    </KundliContext.Provider>
  );
};

export const useKundli = () => useContext(KundliContext);

export default KundliContext;
