package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PropertyDTO;
import com.app.entity.Property;
import com.app.entity.Property.PropertyStatus;
import com.app.entity.User;
import com.app.repository.PropertyRepository;
import com.app.repository.UserRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setAddress(propertyDTO.getAddress());
        property.setDescription(propertyDTO.getDescription());
        property.setRent(propertyDTO.getRent());
        property.setStatus(propertyDTO.getStatus());
        property.setAvailableFrom(propertyDTO.getAvailableFrom());
        property.setAvailableTo(propertyDTO.getAvailableTo());
        property.setImageURLs(propertyDTO.getImageURLs());

        Optional<User> landlord = userRepository.findById(propertyDTO.getLandlordID());
        landlord.ifPresent(property::setLandlord);

        property = propertyRepository.save(property);
        return convertToDTO(property);
    }

    @Override
    @Transactional
    public PropertyDTO updateProperty(int propertyId, PropertyDTO propertyDTO) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
            property.setAddress(propertyDTO.getAddress());
            property.setDescription(propertyDTO.getDescription());
            property.setRent(propertyDTO.getRent());
            property.setStatus(propertyDTO.getStatus());
            property.setAvailableFrom(propertyDTO.getAvailableFrom());
            property.setAvailableTo(propertyDTO.getAvailableTo());
            property.setImageURLs(propertyDTO.getImageURLs());

            Optional<User> landlord = userRepository.findById(propertyDTO.getLandlordID());
            landlord.ifPresent(property::setLandlord);

            property = propertyRepository.save(property);
            return convertToDTO(property);
        }
        throw new RuntimeException("Property not found");
    }

    @Override
    public PropertyDTO getPropertyById(int propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        return property.map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Property not found"));
    }

    @Override
    @Transactional
    public void deleteProperty(int propertyId) {
        if (!propertyRepository.existsById(propertyId)) {
            throw new RuntimeException("Property not found");
        }
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByLandlord(int landlordId) {
        List<Property> properties = propertyRepository.findByLandlord_UserId(landlordId);
        return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByStatus(PropertyStatus status) {
        List<Property> properties = propertyRepository.findByStatus(status);
        return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PropertyDTO convertToDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPropertyID(property.getPropertyID());
        propertyDTO.setLandlordID(property.getLandlord().getUserId()); // Corrected method name
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setRent(property.getRent());
        propertyDTO.setStatus(property.getStatus());
        propertyDTO.setAvailableFrom(property.getAvailableFrom());
        propertyDTO.setAvailableTo(property.getAvailableTo());
        propertyDTO.setImageURLs(property.getImageURLs());
        return propertyDTO;
    }
}
