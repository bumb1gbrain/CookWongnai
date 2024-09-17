'use client'
import axios from 'axios';
import { GetServerSideProps } from 'next';
import { useState } from 'react';

type Restaurant = {
  id: number;
  name: string;
  address: string;
  cuisine: string;
};

type Review = {
  id: number;
  comment: string;
  rating: number;
  user: { username: string };
};

type RestaurantPageProps = {
  restaurant: Restaurant;
  reviews: Review[];
  id: string;
};

const RestaurantPage = ({ restaurant, reviews, id }: RestaurantPageProps) => {
  const [newReview, setNewReview] = useState<string>('');
  const [rating, setRating] = useState<number>(0);
  const [liked, setLiked] = useState<boolean>(false);
  const [favorited, setFavorited] = useState<boolean>(false);

  const handleLike = async () => {
    try {
      await axios.post(`/api/restaurants/${id}/like`);
      setLiked(true); // You may want to toggle this based on the current state
    } catch (err) {
      console.error('Failed to like restaurant');
    }
  };

  const handleFavorite = async () => {
    try {
      await axios.post(`/api/restaurants/${id}/favorite`);
      setFavorited(true); // You can toggle this based on state
    } catch (err) {
      console.error('Failed to favorite restaurant');
    }
  };

  const handleReviewSubmit = async () => {
    try {
      const reviewData = {
        comment: newReview,
        rating,
        restaurantId: id,
      };
      const response = await axios.post(`/api/reviews`, reviewData);
      setNewReview('');
      setRating(0);
    } catch (err) {
      console.error('Failed to post review');
    }
  };

  return (
    <div>
      <h1>{restaurant.name}</h1>
      <p>{restaurant.address}</p>
      <p>Cuisine: {restaurant.cuisine}</p>

      <div style={{ margin: '20px 0' }}>
        <button onClick={handleLike} style={buttonStyle}>
          {liked ? 'Liked' : 'Like'}
        </button>
        <button onClick={handleFavorite} style={buttonStyle}>
          {favorited ? 'Favorited' : 'Add to Favorites'}
        </button>
      </div>

      <div>
        <h2>Reviews</h2>
        {reviews.length > 0 ? (
          reviews.map((review) => (
            <div key={review.id} style={reviewStyle}>
              <p>{review.comment}</p>
              <p>Rating: {review.rating}</p>
              <p>By: {review.user.username}</p>
            </div>
          ))
        ) : (
          <p>No reviews yet.</p>
        )}
      </div>

      <div>
        <h2>Add a Review</h2>
        <textarea
          value={newReview}
          onChange={(e) => setNewReview(e.target.value)}
          rows={4}
          cols={50}
          placeholder="Write your review here..."
        />
        <br />
        <label>
          Rating:
          <input
            type="number"
            value={rating}
            onChange={(e) => setRating(Number(e.target.value))}
            min="1"
            max="5"
          />
        </label>
        <br />
        <button onClick={handleReviewSubmit} style={buttonStyle}>
          Submit Review
        </button>
      </div>
    </div>
  );
};

// Basic styles for buttons and reviews
const buttonStyle = {
  padding: '10px 15px',
  backgroundColor: '#0070f3',
  color: '#fff',
  border: 'none',
  borderRadius: '5px',
  cursor: 'pointer',
  marginRight: '10px',
};

const reviewStyle = {
  border: '1px solid #ccc',
  padding: '10px',
  margin: '10px 0',
};

export default RestaurantPage;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { id } = context.params!; // Extracting the restaurant ID from the URL params

  try {
    // Fetch restaurant data
    const restaurantResponse = await axios.get(`http://localhost:8080/api/restaurants/${id}`);
    const reviewResponse = await axios.get(`http://localhost:8080/api/reviews/restaurant/${id}`);

    return {
      props: {
        restaurant: restaurantResponse.data,
        reviews: reviewResponse.data,
        id,
      },
    };
  } catch (err) {
    console.error('Error fetching restaurant data:', err);
    return {
      props: {
        restaurant: null,
        reviews: [],
        id,
      },
    };
  }
};