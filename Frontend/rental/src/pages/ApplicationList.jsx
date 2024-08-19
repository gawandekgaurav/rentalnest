import React, { useEffect, useState } from 'react';
import { Button, Form } from 'react-bootstrap';
import { getAllApplications, updateApplicationStatus } from '../services/applicationService';
import './ApplicationList.css'; // Import the CSS file

const ApplicationList = () => {
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [status, setStatus] = useState({}); // To track status of each application

  useEffect(() => {
    const fetchApplications = async () => {
      try {
        const data = await getAllApplications();
        setApplications(data);
        const initialStatus = data.reduce((acc, app) => {
          acc[app.applicationID] = app.status;
          return acc;
        }, {});
        setStatus(initialStatus);
      } catch (err) {
        setError('Failed to fetch applications. Please try again later.');
        console.error('Error fetching applications:', err); // Log error for debugging
      } finally {
        setLoading(false);
      }
    };

    fetchApplications();
  }, []);

  const handleStatusChange = (applicationID, newStatus) => {
    setStatus((prevStatus) => ({ ...prevStatus, [applicationID]: newStatus }));
  };

  const handleUpdateStatus = async (applicationID) => {
    try {
      await updateApplicationStatus(applicationID, status[applicationID]);
      alert('Application status updated successfully');
    } catch (err) {
      setError('Failed to update application status.');
      console.error('Error updating status:', err); // Log error for debugging
    }
  };

  if (loading) {
    return <div>Loading applications...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="application-list-container">
      <h1>Applications on Your Properties</h1>
      <table className="application-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Property ID</th>
            <th>Tenant ID</th>
            <th>Application Date</th>
            <th>Status</th>
            <th>Actions</th> {/* Add a column for actions */}
          </tr>
        </thead>
        <tbody>
          {applications.length > 0 ? (
            applications.map((application) => (
              <tr key={application.applicationID}>
                <td>{application.applicationID}</td>
                <td>{application.propertyID}</td>
                <td>{application.tenantID}</td>
                <td>{new Date(application.applicationDate).toLocaleDateString()}</td>
                <td>
                  <Form.Control
                    as="select"
                    value={status[application.applicationID]}
                    onChange={(e) => handleStatusChange(application.applicationID, e.target.value)}
                  >
                    <option value="Pending">Pending</option>
                    <option value="Accepted">Accepted</option>
                    <option value="Rejected">Rejected</option>
                  </Form.Control>
                </td>
                <td>
                  <Button
                    variant="primary"
                    onClick={() => handleUpdateStatus(application.applicationID)}
                  >
                    Update Status
                  </Button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6">No applications found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ApplicationList;
