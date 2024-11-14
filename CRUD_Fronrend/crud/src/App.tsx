import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './components/home';
import UserPage from './components/userPage';
import AdminPage from './components/adminPage';
import Loading from './components/loading';

const App: React.FC = () => {
  return (
    <>
    {/* <Loading/> */}
    <BrowserRouter>  {/* Wrap everything in BrowserRouter */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/user" element={<UserPage />} />
        <Route path="/admin" element={<AdminPage />} />
      </Routes>
    </BrowserRouter>
    </>
  );
}

export default App;
