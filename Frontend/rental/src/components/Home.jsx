import React from "react";
import { Link, useNavigate } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import "./Home.css";
import SignIn from "./SignIn";
import SignUp from "./SignUp";
import UserList from "../pages/UserList";
import PropertyList from "../pages/PropertyList"; 
import ApplicationList from "../pages/ApplicationList";// Import PropertyList here

import { logout } from "../services/authService";

function Home() {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <>
      <Navbar expand="lg" className="navbar-custom">
        <Container>
          <Navbar.Brand as={Link} to="/">
            RENTALNEST
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link as={Link} to="/">
                Home
              </Nav.Link>
              <Nav.Link as={Link} to="/about-us">
                About Us
              </Nav.Link>
              <Nav.Link as={Link} to="/contact-us">
                Contact Us
              </Nav.Link>
              <Nav.Link as={Link} to="/sign-in">
                Sign In
              </Nav.Link>
              <Nav.Link as={Link} to="/sign-up">
                Sign Up
              </Nav.Link>
              <Nav.Link as={Link} to="/property-register">
                PropertyRegister
              </Nav.Link>
              <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
              <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                <NavDropdown.Item as={Link} to="/user-list">
                  UserList
                </NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/property-list">
                  PropertyList
                </NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/application-list">
                  ApplicationList
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <div className="full-page-image-container">
        <div className="form-overlay">
          {/* Remove routes here to avoid nested routes */}
        </div>
        <div>
          <h1>Welcome To RENTALNEST</h1>
        </div>
      </div>
    </>
  );
}

export default Home;
