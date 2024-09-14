'use client'

import { Button } from "@/components/ui/button";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react"

export default function CreateRestaurant() {
  const [name, setName] = useState('');
  const [location, setLocation] = useState('');
  const [cuisine, setCuisine] = useState('');
  const router = useRouter();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    axios.post('/api/restaurants', {name, location, cuisine})
      .then(() => router.push('/'))
      .catch(error => console.log(error));
  };

  return(
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name:</label>
        <input type="text" value={name} onChange={e => setName(e.target.value)} />
      </div>
      <div>
        <label>Location:</label>
        <input type="text" value={location} onChange={e => setLocation(e.target.value)} />
      </div>
      <div>
        <label>Cuisine:</label>
        <input type="text" value={cuisine} onChange={e => setCuisine(e.target.value)} />
      </div>
      <Button type="submit">Create Restaurant</Button>
    </form>
  );
}
