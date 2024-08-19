import React, { useState } from "react";
import { Form, Button, Container, Alert } from "react-bootstrap";
import { signIn } from "../services/userService";
import { toast, ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom";
import "./SignIn.css";
import "react-toastify/dist/ReactToastify.css";

function SignIn() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await signIn(email, password);
      setError("");
      toast.success("Login successful!");
      // Store token and userId in localStorage
      localStorage.setItem("token", data.token);
      localStorage.setItem("userId", data.userId);
      navigate("/property-register");
    } catch (error) {
      setError(error.message);
      toast.error("Login failed: " + error.message);
    }
  };

  return (
    <Container className="login-wrapper">
      <div className="login-form-container">
        <h2 className="login-title">Sign In</h2>
        {error && <Alert variant="danger">{error}</Alert>}
        <Form onSubmit={handleSubmit} className="login-form">
          <Form.Group controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>

          <Button variant="primary" type="submit" className="login-button">
            Sign In
          </Button>
        </Form>
        <ToastContainer />
      </div>
    </Container>
  );
}

export default SignIn;
