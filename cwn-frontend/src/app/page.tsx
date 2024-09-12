'use client'

import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';


interface Restaurant {
  id: number;
  name: string;
  location: string;
  cuisine: string;
}

export default function RestaurantList() {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);

  useEffect(() => {
    axios.get<Restaurant[]>('/api/restaurants')
      .then(response => setRestaurants(response.data))
      .catch(error => console.error(error));
  }, []);

  return (
    <div>
      <h1>Restaurants</h1>
      <ul>
        {restaurants.map((restaurant) => (
          <li key={restaurant.id}>
            {restaurant.name} - {restaurant.cuisine} - {restaurant.location}
          </li>
        ))}
      </ul>
    </div>
  );
}