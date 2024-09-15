'use client'
// /pages/restaurants.tsx
import axios from 'axios';
import { useEffect, useState } from 'react';
import Link from 'next/link';

type Restaurant = {
  id: number;
  name: string;
  location: string;
  cuisine: string;
};

const Restaurants = () => {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchRestaurants = async () => {
      try {
        const response = await axios.get('/api/restaurants'); // Adjust this URL to your backend API
        setRestaurants(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to load restaurants');
        setLoading(false);
      }
    };

    fetchRestaurants();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h1>Restaurants</h1>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '20px' }}>
        {restaurants.map((restaurant) => (
          <div className="border border-gray-300 rounded-lg p-5 text-center shadow-md" key={restaurant.id} >
            <h2>{restaurant.name}</h2>
            <p>{restaurant.location}</p>
            <p>Cuisine: {restaurant.cuisine}</p>
            <Link href={`/restaurants/${restaurant.id}`}>
              <div style={buttonStyle}>View Details</div>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

// Basic styles for cards and buttons (you can move these to a CSS module or styled-components)
// const cardStyle = {
//   border: '1px solid #ccc',
//   borderRadius: '10px',
//   padding: '20px',
//   textAlign: 'center',
//   boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
// };

const buttonStyle = {
  display: 'inline-block',
  padding: '10px 15px',
  backgroundColor: '#0070f3',
  color: '#fff',
  textDecoration: 'none',
  borderRadius: '5px',
  marginTop: '10px',
};

export default Restaurants;