'use client'

import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import './globals.css';


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
    <div className="bg-gray-100 h-screen">
      {/* Header */}
      {/* <header className="bg-white p-4 shadow-md sticky top-0 z-10">
        <div className="container mx-auto flex justify-between items-center">
          <h1 className="text-xl font-bold text-blue-600">Cook Wongnai</h1>
          <input
            className="border border-gray-300 rounded-full px-4 py-2 w-1/3"
            type="text"
            placeholder="Search X"
          />
        </div>
      </header> */}

      {/* Main Content */}
      <main className="container mx-auto flex mt-8">
        {/* Sidebar */}
        <aside className="w-1/4 p-4">
          <div className="bg-white p-4 shadow rounded-lg">
            <h2 className="font-bold text-gray-900">Trending</h2>
            <ul>
              <li className="text-gray-900">#MarsMission</li>
              <li className="text-gray-900">#AIRevolution</li>
              <li className="text-gray-900">#CryptoCrash</li>
              <li className="text-gray-900">#EBUMAOKPAI</li>
            </ul>
          </div>
        </aside>

        {/* Tweet Feed */}
        <section className="w-1/2 p-4">
          <div className="bg-white p-4 shadow rounded-lg">
            <textarea
              placeholder="What's happening?"
              className="w-full p-2 border border-gray-300 rounded-lg"
            ></textarea>
            <button className="bg-blue-500 text-white px-4 py-2 rounded-lg mt-2">
              Tweet
            </button>
          </div>
          <div className="mt-4">
            {restaurants.map((restaurant) => (
              <div key={restaurant.id} className="bg-white p-4 shadow rounded-lg text-gray-900">
                <h3 className="font-bold text-gray-900">{restaurant.name}</h3>
                <p>{restaurant.location}</p>
                <div className="text-gray-500 text-sm">
                  {restaurant.cuisine} likes
                </div>
              </div>
            ))}
          </div>
        </section>

        {/* Right Sidebar */}
        <aside className="w-1/4 p-4">
          <div className="bg-white p-4 shadow rounded-lg">
            <h2 className="font-bold">Who to follow</h2>
            <ul>
              <li className="text-gray-900">Elon Musk</li>
              <li className="text-gray-900">Bill Gates</li>
              <li className="text-gray-900">Sundar Pichai</li>
              <li className="text-gray-900">Tatar Ruk Nong Pa tong kho</li>
            </ul>
          </div>
        </aside>
      </main>
    </div>
  );
};

