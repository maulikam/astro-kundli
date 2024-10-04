import { useContext } from 'react';
import KundliContext from '../context/KundliContext';

const useKundliApi = () => {
  const context = useContext(KundliContext);

  return {
    ...context,
  };
};

export default useKundliApi;
