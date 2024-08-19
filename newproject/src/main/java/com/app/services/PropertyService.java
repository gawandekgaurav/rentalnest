package com.app.services;

import java.util.List;

import com.app.dto.PropertyDTO;
import com.app.entity.Property.HouseType;
import com.app.entity.Property.PropertyStatus;

public interface PropertyService {
	PropertyDTO createProperty(PropertyDTO propertyDTO);

	PropertyDTO updateProperty(int propertyId, PropertyDTO propertyDTO);

	PropertyDTO getPropertyById(int propertyId);

	void deleteProperty(int propertyId);

	List<PropertyDTO> getAllProperties();

	List<PropertyDTO> getPropertiesByStatus(PropertyStatus status);

	List<PropertyDTO> getPropertiesByHouseType(HouseType houseType); // Add method to get properties by houseType

	List<PropertyDTO> getPropertiesByCityArea(String cityArea); // Add method to get properties by cityArea

}
