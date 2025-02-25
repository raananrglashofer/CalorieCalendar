import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Link } from "react-router-dom";
import Users from './Pages/Users';
import Calendar from './Pages/Calendar';
import Home from './Pages/Home';

function App() {
    return (
      <Router>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/users" element={<Users />} />
            <Route path="/calendar" element={<Calendar />} />
        </Routes>
      </Router>
    );
}

export default App;