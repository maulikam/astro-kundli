import { useContext } from 'react';
import KundliContext from '../context/KundliContext';

/**
 * Custom hook to access the Kundli context
 * @returns {Object} - The current context value
 */
const useKundli = () => {
  return useContext(KundliContext);
};

export default useKundli;
