import React, { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { getAllProperties } from '../services/propertyService';
import './PropertyList.css'; // Import CSS for styling

const PropertyList = () => {
  const [properties, setProperties] = useState([]);
  const navigate = useNavigate(); // Use the navigate hook for routing

  useEffect(() => {
    const fetchProperties = async () => {
      try {
        const data = await getAllProperties();
        setProperties(data);
      } catch (error) {
        console.error('Error fetching properties:', error);
      }
    };

    fetchProperties();
  }, []);

  const handleApplyClick = (propertyID) => {    
    navigate(`/application?propertyId=${propertyID}`); // Navigate to the Application page with propertyID
  };

  return (
    <div className="property-list">
      <h2>Property List</h2>
      <Table striped bordered hover size="sm">
        <thead>
          <tr>
            <th>#</th>
            <th>Landlord ID</th>
            <th>Address</th>
            <th>Description</th>
            <th>Rent</th>
            <th>Status</th>
            <th>Available From</th>
            <th>Available To</th>
            <th>House Type</th>
            <th>City Area</th>
            <th>Image</th>
            <th>Actions</th> {/* Add a column for actions */}
          </tr>
        </thead>
        <tbody>
          {properties.map((property) => (
            <tr key={property.propertyID}>
              <td>{property.propertyID}</td>
              <td>{property.landlordID}</td>
              <td>{property.address}</td>
              <td>{property.description}</td>
              <td>${property.rent.toFixed(2)}</td>
              <td>{property.status}</td>
              <td>{new Date(property.availableFrom).toLocaleDateString()}</td>
              <td>{property.availableTo ? new Date(property.availableTo).toLocaleDateString() : 'N/A'}</td>
              <td>{property.houseType}</td>
              <td>{property.cityArea}</td>
              <td>
                {property.imageData ? (
                  <img
                    src={`/images/${property.imageData}`}
                    alt="Property"
                    style={{ width: '100px', height: 'auto' }}
                  />
                ) : (
                  'No image'
                )}
              </td>
              <td>
                <Button 
                  variant="primary" 
                  onClick={() => handleApplyClick(property.propertyID)} // Handle button click
                >
                  Apply
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default PropertyList;
