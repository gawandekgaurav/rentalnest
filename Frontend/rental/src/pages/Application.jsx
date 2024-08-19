import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { createApplication } from '../services/applicationService';
import './Application.css'; // Import the CSS file

const Application = () => {
  const queryParams = new URLSearchParams(location.search);
  const propertyID = queryParams.get('propertyId');
  // const { propertyID } = useParams();
  // console.log("Property id : ", id);
  const navigate = useNavigate();

  const [applicationDate] = useState(new Date().toISOString().slice(0, 10)); // Default to today's date
  const [status] = useState('Pending');
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const tenantID = localStorage.getItem('userId'); // Assuming tenant ID is stored in localStorage
    console.log("Tanent ID : ",tenantID);

    try {
      await createApplication({
        propertyID: parseInt(propertyID, 10),
        tenantID: parseInt(tenantID, 10),
        applicationDate,
        status,
      });
      navigate('/'); // Redirect to application list or success page
    } catch (err) {
      setError('Error creating application: ' + err.message); // Provide user-friendly error message
    }
  };

  return (
    <div className="application-page-container">
      <h1>Apply for Property</h1>
      <form onSubmit={handleSubmit} className="application-form">
        <label htmlFor="applicationDate">Application Date:</label>
        <input
          type="date"
          id="applicationDate"
          value={applicationDate}
          readOnly
        />

        <label htmlFor="status">Status:</label>
        <input
          type="text"
          id="status"
          value={status}
          readOnly
        />

        <button type="submit">Submit Application</button>

        {error && <div className="error-message">{error}</div>}
      </form>
    </div>
  );
};

export default Application;
