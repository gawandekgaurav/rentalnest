package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.PropertyDTO;
import com.app.entity.Property.HouseType;
import com.app.entity.Property.PropertyStatus;
import com.app.services.PropertyService;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin("*")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	// Directory for storing images
	private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/webapp/images/";

	// Create a property with image upload
	@PostMapping("/create")

	public ResponseEntity<PropertyDTO> createProperty(@ModelAttribute PropertyDTO propertyDTO,
			@RequestParam("image") MultipartFile file) throws IOException {
		// Check if file is not empty
		if (!file.isEmpty()) {
			// Generate unique file name and save file to disk
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIRECTORY + fileName);
			Files.write(filePath, file.getBytes());

			// Set image name in propertyDTO
			propertyDTO.setImageData(fileName);
		}

		// Create property
		PropertyDTO createdProperty = propertyService.createProperty(propertyDTO);

		// Return created property with image info
		return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
	}

	// Update property
	@PutMapping("/update/{propertyId}")

	public ResponseEntity<PropertyDTO> updateProperty(@PathVariable int propertyId,
			@ModelAttribute PropertyDTO propertyDTO,
			@RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
		// If an image is provided, update the image as well
		if (file != null && !file.isEmpty()) {
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIRECTORY + fileName);
			Files.write(filePath, file.getBytes());

			// Set new image name in propertyDTO
			propertyDTO.setImageData(fileName);
		}

		// Update the property
		PropertyDTO updatedProperty = propertyService.updateProperty(propertyId, propertyDTO);

		return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
	}

	// Get property by ID
	@GetMapping("/getbyid/{propertyId}")
	public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable int propertyId) {
		PropertyDTO propertyDTO = propertyService.getPropertyById(propertyId);
		return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
	}

	// Delete property
	@DeleteMapping("/delete/{propertyId}")
	@PreAuthorize("hasRole('LANDLORD')")
	public ResponseEntity<Void> deleteProperty(@PathVariable int propertyId) {
		propertyService.deleteProperty(propertyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Get all properties
	@GetMapping("/getall")
	public ResponseEntity<List<PropertyDTO>> getAllProperties() {
		List<PropertyDTO> properties = propertyService.getAllProperties();
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	// Get properties by status
	@GetMapping("/status/{status}")
	public ResponseEntity<List<PropertyDTO>> getPropertiesByStatus(@PathVariable PropertyStatus status) {
		List<PropertyDTO> properties = propertyService.getPropertiesByStatus(status);
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	// Get properties by house type
	@GetMapping("/housetype/{houseType}")
	public ResponseEntity<List<PropertyDTO>> getPropertiesByHouseType(@PathVariable HouseType houseType) {
		List<PropertyDTO> properties = propertyService.getPropertiesByHouseType(houseType);
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	// Get properties by city area
	@GetMapping("/cityarea/{cityArea}")
	public ResponseEntity<List<PropertyDTO>> getPropertiesByCityArea(@PathVariable String cityArea) {
		List<PropertyDTO> properties = propertyService.getPropertiesByCityArea(cityArea);
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}
}
