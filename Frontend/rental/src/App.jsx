import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import UserList from "./pages/UserList";
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import AboutUs from "./components/AboutUs";
import ContactUs from "./components/ContactUs";
import PropertyList from "./pages/PropertyList";
import PropertyRegister from "./pages/PropertyRegister"; // Import PropertyRegister
import Application from "./pages/Application";
import ApplicationList from "./pages/ApplicationList";
import "bootstrap/dist/css/bootstrap.min.css";
import { ToastContainer } from 'react-toastify';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about-us" element={<AboutUs />} />
        <Route path="/contact-us" element={<ContactUs />} />
        <Route path="/sign-in" element={<SignIn />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/user-list" element={<UserList />} />
        <Route path="/property-list" element={<PropertyList />} />
        <Route path="/property-register" element={<PropertyRegister />} /> {/* Add this route */}
        <Route path="/application" element={<Application />} />
        <Route path="/application-list" element={<ApplicationList />} />
      </Routes>
    </Router>
  );
}



export default App;
