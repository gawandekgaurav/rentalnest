package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PropertyDTO;
import com.app.entity.Property;
import com.app.entity.Property.HouseType;
import com.app.entity.Property.PropertyStatus;
import com.app.repository.PropertyRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PropertyDTO createProperty(PropertyDTO propertyDTO) {
		Property property = new Property();
		property.setLandlord(userRepository.findById(propertyDTO.getLandlordID())
				.orElseThrow(() -> new RuntimeException("Landlord not found")));
		property.setAddress(propertyDTO.getAddress());
		property.setDescription(propertyDTO.getDescription());
		property.setRent(propertyDTO.getRent());
		property.setStatus(PropertyStatus.AVAILABLE);
		property.setAvailableFrom(propertyDTO.getAvailableFrom());
		property.setAvailableTo(propertyDTO.getAvailableTo());
		property.setHouseType(propertyDTO.getHouseType());
		property.setCityArea(propertyDTO.getCityArea());

		// Set image data (image name) as string if provided
		if (propertyDTO.getImageData() != null && !propertyDTO.getImageData().isEmpty()) {
			property.setImageData(propertyDTO.getImageData());
		}

		Property savedProperty = propertyRepository.save(property);

		// Map back to DTO
		propertyDTO.setPropertyID(savedProperty.getPropertyID());
		return propertyDTO;
	}

	@Override
	public PropertyDTO updateProperty(int propertyId, PropertyDTO propertyDTO) {
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));
		property.setAddress(propertyDTO.getAddress());
		property.setDescription(propertyDTO.getDescription());
		property.setRent(propertyDTO.getRent());
		property.setAvailableFrom(propertyDTO.getAvailableFrom());
		property.setAvailableTo(propertyDTO.getAvailableTo());
		property.setHouseType(propertyDTO.getHouseType());
		property.setCityArea(propertyDTO.getCityArea());

		// Update image data (image name) as string if provided
		if (propertyDTO.getImageData() != null && !propertyDTO.getImageData().isEmpty()) {
			property.setImageData(propertyDTO.getImageData());
		}

		Property updatedProperty = propertyRepository.save(property);

		// Map back to DTO
		propertyDTO.setPropertyID(updatedProperty.getPropertyID());
		return propertyDTO;
	}

	@Override
	public PropertyDTO getPropertyById(int propertyId) {
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));
		return mapToDTO(property);
	}

	@Override
	public void deleteProperty(int propertyId) {
		propertyRepository.deleteById(propertyId);
	}

	@Override
	public List<PropertyDTO> getAllProperties() {
		return propertyRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<PropertyDTO> getPropertiesByStatus(PropertyStatus status) {
		return propertyRepository.findByStatus(status).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<PropertyDTO> getPropertiesByHouseType(HouseType houseType) {
		return propertyRepository.findByHouseType(houseType).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<PropertyDTO> getPropertiesByCityArea(String cityArea) {
		return propertyRepository.findByCityArea(cityArea).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// Helper method to map Property entity to PropertyDTO
	private PropertyDTO mapToDTO(Property property) {
		PropertyDTO dto = new PropertyDTO();
		dto.setPropertyID(property.getPropertyID());
		dto.setLandlordID(property.getLandlord().getUserId());
		dto.setAddress(property.getAddress());
		dto.setDescription(property.getDescription());
		dto.setRent(property.getRent());
		dto.setStatus(property.getStatus());
		dto.setAvailableFrom(property.getAvailableFrom());
		dto.setAvailableTo(property.getAvailableTo());
		dto.setHouseType(property.getHouseType());
		dto.setCityArea(property.getCityArea());
		dto.setImageData(property.getImageData()); // Map image name to DTO
		return dto;
	}
}
