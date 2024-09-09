const express = require('express');
const bodyParser = require('body-parser');
const { Pool } = require('pg');

const app = express();
const port = 3000; // Change this to your desired port

// Configure bodyParser to parse JSON data
app.use(bodyParser.json());

// PostgreSQL database configuration
const pool = new Pool({
  user: 'your_postgres_user',
  host: 'localhost',
  database: 'mydb', // Replace with your database name
  password: 'your_password',
  port: 5432, // Default PostgreSQL port
});

// Define a route to register a new user
app.post('/register', (req, res) => {
  const { username, password } = req.body;

  console.log('Received a registration request for username:', username);

  // Check if the username is already taken
  pool.query('SELECT * FROM users WHERE username = $1', [username], (err, result) => {
    if (err) {
      console.error('Error while checking username:', err);
      return res.status(500).json({ error: 'Internal server error' });
    }

    if (result.rows.length > 0) {
      console.log('Username already exists:', username);
      return res.status(400).json({ error: 'Username already exists' });
    }

    // Insert the new user into the database
    pool.query('INSERT INTO users (username, password) VALUES ($1, $2)', [username, password], (err) => {
      if (err) {
        console.error('Error while inserting new user:', err);
        return res.status(500).json({ error: 'Internal server error' });
      }

      console.log('User registered successfully:', username);
      res.status(201).json({ message: 'User registered successfully' });
    });
  });
});

// Define a route to validate user credentials
app.post('/login', (req, res) => {
  const { username, password } = req.body;

  console.log('Received a login request for username:', username);

  // Check if the credentials are valid
  pool.query('SELECT * FROM users WHERE username = $1 AND password = $2', [username, password], (err, result) => {
    if (err) {
      console.error('Error while checking credentials:', err);
      return res.status(500).json({ error: 'Internal server error' });
    }

    if (result.rows.length === 0) {
      console.log('Invalid credentials for username:', username);
      return res.status(401).json({ error: 'Invalid credentials' });
    }

    console.log('Login successful for username:', username);
    res.json({ message: 'Login successful' });
  });
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
