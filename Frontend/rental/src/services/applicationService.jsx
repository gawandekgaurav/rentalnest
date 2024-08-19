import axios from 'axios';

const API_URL = 'http://localhost:8080/api/applications';

export const getAllApplications = async () => {
  try {
    const response = await axios.get(`${API_URL}/getallapplications`);
    return response.data;
  } catch (error) {
    console.error('Error fetching applications:', error);
    throw error;
  }
};

export const getApplicationById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/getbyid/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching application with id ${id}:`, error);
    throw error;
  }
};

export const createApplication = async (applicationDTO) => {
  try {
    const response = await axios.post(`${API_URL}/create`, applicationDTO);
    return response.data;
  } catch (error) {
    console.error('Error creating application:', error);
    throw error;
  }
};

export const updateApplication = async (id, applicationDTO) => {
  try {
    const response = await axios.put(`${API_URL}/update/${id}`, applicationDTO);
    return response.data;
  } catch (error) {
    console.error(`Error updating application with id ${id}:`, error);
    throw error;
  }
};

export const deleteApplication = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error(`Error deleting application with id ${id}:`, error);
    throw error;
  }
};

export const getApplicationsByProperty = async (propertyId) => {
  try {
    const response = await axios.get(`${API_URL}/property/${propertyId}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching applications for property ${propertyId}:`, error);
    throw error;
  }
};

export const getApplicationsByLandlord = async (landlordId) => {
  try {
    const response = await axios.get(`${API_URL}/landlord/${landlordId}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching applications for landlord ${landlordId}:`, error);
    throw error;
  }
};

export const getApplicationsByTenant = async (tenantId) => {
  try {
    const response = await axios.get(`${API_URL}/tenant/${tenantId}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching applications for tenant ${tenantId}:`, error);
    throw error;
  }
};

export const updateApplicationStatus = async (id, status) => {
  try {
    const response = await axios.put(`${API_URL}/update-status/${id}`, { status });
    return response.data;
  } catch (error) {
    console.error(`Error updating status for application with id ${id}:`, error);
    throw error;
  }
};
