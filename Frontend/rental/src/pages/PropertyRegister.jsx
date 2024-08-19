import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createProperty } from '../services/propertyService';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './PropertyRegister.css';

const PropertyRegister = () => {
  const navigate = useNavigate(); // Use useNavigate to handle navigation
  const [property, setProperty] = useState({
    propertyID: '', // Add propertyID to state
    landlordID: '',
    address: '',
    description: '',
    rent: '',
    status: 'AVAILABLE', // default status
    availableFrom: '',
    availableTo: '',
    houseType: 'ONE_BHK', // default house type
    cityArea: '',
  });

  const [imageFile, setImageFile] = useState(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProperty({ ...property, [name]: value });
  };

  const handleImageChange = (e) => {
    setImageFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Use FormData to handle file uploads
      const formData = new FormData();
      for (const [key, value] of Object.entries(property)) {
        formData.append(key, value);
      }
      if (imageFile) {
        formData.append('image', imageFile);
      }

      await createProperty(formData);
      toast.success('Property created successfully!');
      setProperty({
        propertyID: '',
        landlordID: '',
        address: '',
        description: '',
        rent: '',
        status: 'AVAILABLE',
        availableFrom: '',
        availableTo: '',
        houseType: 'ONE_BHK',
        cityArea: '',
      });
      setImageFile(null);
    } catch (error) {
      toast.error('Error creating property.');
    }
  };

  const handleViewApplications = () => {
    navigate('/application-list'); // Navigate to the ApplicationList page
  };

  return (
    <div className="property-register">
      <h2>Register Property</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Property ID:
          <input type="number" name="propertyID" value={property.propertyID} onChange={handleInputChange} />
        </label>
        <label>
          Landlord ID:
          <input type="number" name="landlordID" value={property.landlordID} onChange={handleInputChange} required />
        </label>
        <label>
          Address:
          <input type="text" name="address" value={property.address} onChange={handleInputChange} required />
        </label>
        <label>
          Description:
          <textarea name="description" value={property.description} onChange={handleInputChange} required />
        </label>
        <label>
          Rent:
          <input type="number" step="0.01" name="rent" value={property.rent} onChange={handleInputChange} required />
        </label>
        <label>
          Status:
          <select name="status" value={property.status} onChange={handleInputChange} required>
            <option value="AVAILABLE">Available</option>
            <option value="RENTED">Rented</option>
          </select>
        </label>
        <label>
          Available From:
          <input type="date" name="availableFrom" value={property.availableFrom} onChange={handleInputChange} required />
        </label>
        <label>
          Available To:
          <input type="date" name="availableTo" value={property.availableTo} onChange={handleInputChange} />
        </label>
        <label>
          House Type:
          <select name="houseType" value={property.houseType} onChange={handleInputChange} required>
            <option value="ONE_BHK">1BHK</option>
            <option value="TWO_BHK">2BHK</option>
            <option value="THREE_BHK">3BHK</option>
          </select>
        </label>
        <label>
          City Area:
          <input type="text" name="cityArea" value={property.cityArea} onChange={handleInputChange} required />
        </label>
        <label>
          Image:
          <input type="file" name="image" onChange={handleImageChange} />
        </label>
        <button type="submit">Register Property</button>
        <button type="button" onClick={handleViewApplications} className="view-applications-button">
          View Applications
        </button>
      </form>
    </div>
  );
};

export default PropertyRegister;
