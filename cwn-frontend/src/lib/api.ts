// lib/api.ts
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const getAllRestaurants = async () => {
  const response = await axios.get(`${API_BASE_URL}/restaurants`);
  return response.data;
};

export const getRestaurantById = async (id: number) => {
  const response = await axios.get(`${API_BASE_URL}/restaurants/${id}`);
  return response.data;
};

export const createReview = async (restaurantId: number, reviewData: { content: string; rating: number }) => {
  const response = await axios.post(`${API_BASE_URL}/restaurants/${restaurantId}/reviews`, reviewData);
  return response.data;
};
