// src/store/index.js

import { configureStore } from '@reduxjs/toolkit';
import kundliReducer from './kundliSlice';
import userReducer from './userSlice';
// ... other imports if necessary

// Adding middleware for logging and handling async actions
const middleware = (getDefaultMiddleware) => 
  getDefaultMiddleware().concat(/* additional middleware if needed */);

const store = configureStore({
  reducer: {
    kundli: kundliReducer,
    user: userReducer,
  },
  middleware, // Use the middleware defined above
  devTools: process.env.NODE_ENV !== 'production', // Enable Redux DevTools in development mode
});

export default store;
