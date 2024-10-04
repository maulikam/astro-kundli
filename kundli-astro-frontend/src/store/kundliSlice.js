// src/store/kundliSlice.js

import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { kundliService } from '../services/kundliService';
import { error as logError, info as logInfo } from '../lib/logService';

// Async actions for interacting with the API
export const fetchKundlis = createAsyncThunk('kundli/fetchKundlis', async (_, { rejectWithValue }) => {
  try {
    logInfo('Fetching Kundlis');
    return await kundliService.getAllKundlis();
  } catch (error) {
    logError('Failed to fetch Kundlis:', error);
    return rejectWithValue(error.message);
  }
});

export const createKundli = createAsyncThunk('kundli/createKundli', async (kundliData, { rejectWithValue }) => {
  try {
    logInfo('Creating a new Kundli');
    return await kundliService.createKundli(kundliData);
  } catch (error) {
    logError('Failed to create Kundli:', error);
    return rejectWithValue(error.message);
  }
});

// Slice for managing Kundli data
const kundliSlice = createSlice({
  name: 'kundli',
  initialState: {
    kundlis: [],
    selectedKundli: null,
    isLoading: false,
    error: null,
  },
  reducers: {
    setSelectedKundli: (state, action) => {
      state.selectedKundli = action.payload;
    },
  },
  extraReducers: (builder) => {
    const handlePending = (state) => {
      state.isLoading = true;
      state.error = null;
    };

    const handleFulfilled = (state, action) => {
      state.isLoading = false;
      state.kundlis.push(action.payload);
    };

    const handleRejected = (state, action) => {
      state.isLoading = false;
      state.error = action.payload;
    };

    builder
      .addCase(fetchKundlis.pending, handlePending)
      .addCase(fetchKundlis.fulfilled, (state, action) => {
        handleFulfilled(state, action);
        state.kundlis = action.payload; // Update the list of kundlis
      })
      .addCase(fetchKundlis.rejected, handleRejected)
      .addCase(createKundli.fulfilled, handleFulfilled)
      .addCase(createKundli.rejected, handleRejected);
  },
});

export const { setSelectedKundli } = kundliSlice.actions;

export default kundliSlice.reducer;
