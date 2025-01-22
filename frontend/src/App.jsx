import React, { useEffect, useState } from 'react';

function App() {
  const [users, setUsers] = useState([]);

  // Fetch data from your Spring Boot backend
  useEffect(() => {
    fetch('/api/users') // Adjust the endpoint to match your Spring Boot API
      .then((response) => response.json())
      .then((data) => setUsers(data))
      .catch((error) => console.error('Error fetching users:', error));
  }, []);

  return (
    <div>
      <h1>Calorie Calendar</h1>
      <h2>User List</h2>
      <ul>
        {users.map((user) => (
          <li key={user.id}>{user.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;