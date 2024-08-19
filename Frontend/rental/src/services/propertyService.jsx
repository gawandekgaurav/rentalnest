import { myAxios } from "./helper";

export const createProperty = async (formData) => {
  try {
    const response = await myAxios.post('/api/properties/create', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
    console.log('Response:', response); // Check the response here
    return response.data;
  } catch (error) {
    console.error('Error:', error); // Log error details
    throw error;
  }
};

export const updateProperty = async (propertyId, propertyDTO, imageFile) => {
  try {
    const formData = new FormData();
    formData.append('propertyDTO', JSON.stringify(propertyDTO));
    if (imageFile) {
      formData.append('image', imageFile);
    }

    const response = await myAxios.put(`/api/properties/update/${propertyId}`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getPropertyById = async (propertyId) => {
  try {
    const response = await myAxios.get(`/api/properties/getbyid/${propertyId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteProperty = async (propertyId) => {
  try {
    const response = await myAxios.delete(`/api/properties/delete/${propertyId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getAllProperties = async () => {
  try {
    const response = await myAxios.get('/api/properties/getall');
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getPropertiesByStatus = async (status) => {
  try {
    const response = await myAxios.get(`/api/properties/status/${status}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getPropertiesByHouseType = async (houseType) => {
  try {
    const response = await myAxios.get(`/api/properties/housetype/${houseType}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getPropertiesByCityArea = async (cityArea) => {
  try {
    const response = await myAxios.get(`/api/properties/cityarea/${cityArea}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
