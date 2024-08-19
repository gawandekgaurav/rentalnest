package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApplicationDTO;
import com.app.entity.Application;
import com.app.entity.Property;
import com.app.entity.User;
import com.app.repository.ApplicationRepository;
import com.app.repository.PropertyRepository;
import com.app.repository.UserRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public ApplicationDTO createApplication(ApplicationDTO applicationDTO) {
		Application application = new Application();
		application.setApplicationDate(applicationDTO.getApplicationDate());
		application.setStatus(applicationDTO.getStatus());

		Optional<Property> property = propertyRepository.findById(applicationDTO.getPropertyID());
		if (property.isPresent()) {
			application.setProperty(property.get());
		} else {
			throw new RuntimeException("Property not found");
		}

		Optional<User> tenant = userRepository.findById(applicationDTO.getTenantID());
		if (tenant.isPresent()) {
			application.setTenant(tenant.get());
		} else {
			throw new RuntimeException("Tenant not found");
		}

		application = applicationRepository.save(application);
		return convertToDTO(application);
	}

	@Override
	@Transactional
	public ApplicationDTO updateApplication(int applicationId, ApplicationDTO applicationDTO) {
		Optional<Application> optionalApplication = applicationRepository.findById(applicationId);
		if (optionalApplication.isPresent()) {
			Application application = optionalApplication.get();
			application.setApplicationDate(applicationDTO.getApplicationDate());
			application.setStatus(applicationDTO.getStatus());

			Optional<Property> property = propertyRepository.findById(applicationDTO.getPropertyID());
			if (property.isPresent()) {
				application.setProperty(property.get());
			} else {
				throw new RuntimeException("Property not found");
			}

			Optional<User> tenant = userRepository.findById(applicationDTO.getTenantID());
			if (tenant.isPresent()) {
				application.setTenant(tenant.get());
			} else {
				throw new RuntimeException("Tenant not found");
			}

			application = applicationRepository.save(application);
			return convertToDTO(application);
		}
		throw new RuntimeException("Application not found");
	}

	@Override
	public ApplicationDTO getApplicationById(int applicationId) {
		Optional<Application> application = applicationRepository.findById(applicationId);
		return application.map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Application not found"));
	}

	@Override
	@Transactional
	public void deleteApplication(int applicationId) {
		if (!applicationRepository.existsById(applicationId)) {
			throw new RuntimeException("Application not found");
		}
		applicationRepository.deleteById(applicationId);
	}

	@Override
	public List<ApplicationDTO> getAllApplications() {
		List<Application> applications = applicationRepository.findAll();
		return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<ApplicationDTO> getApplicationsByProperty(int propertyId) {
		Optional<Property> property = propertyRepository.findById(propertyId);
		if (property.isPresent()) {
			List<Application> applications = applicationRepository.findByProperty(property.get());
			return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
		}
		throw new RuntimeException("Property not found");
	}

	@Override
	public List<ApplicationDTO> getApplicationsByTenant(int tenantId) {
		Optional<User> tenant = userRepository.findById(tenantId);
		if (tenant.isPresent()) {
			List<Application> applications = applicationRepository.findByTenant(tenant.get());
			return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
		}
		throw new RuntimeException("Tenant not found");
	}

//	@Override
//	public List<ApplicationDTO> getApplicationsByLandlord(int landlordId) {
//		Optional<User> landlord = userRepository.findById(landlordId);
//		if (landlord.isPresent()) {
//			List<Application> applications = applicationRepository.findByLandlord(landlord.get()); // Ensure this method is defined in your repository
//			return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
//		}
//		throw new RuntimeException("Landlord not found");
//	}

	@Override
	@Transactional
	public ApplicationDTO updateApplicationStatus(int id, ApplicationDTO applicationDTO) {
		Application existingApplication = applicationRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

		// Update the status of the existing application
		existingApplication.setStatus(applicationDTO.getStatus());

		// Save the updated application
		Application updatedApplication = applicationRepository.save(existingApplication);

		// Convert the updated application entity to DTO
		return convertToDTO(updatedApplication);
	}

	private ApplicationDTO convertToDTO(Application application) {
		ApplicationDTO applicationDTO = new ApplicationDTO();
		applicationDTO.setApplicationID(application.getApplicationID());
		applicationDTO.setPropertyID(application.getProperty().getPropertyID());
		applicationDTO.setTenantID(application.getTenant().getUserId()); // Assuming User entity has getUserId() method
		applicationDTO.setApplicationDate(application.getApplicationDate());
		applicationDTO.setStatus(application.getStatus());
		return applicationDTO;
	}
}
