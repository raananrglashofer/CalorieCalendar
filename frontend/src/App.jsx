import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Users from './components/Users';
import Calendar from './components/Calendar';
import Home from './components/Home';

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