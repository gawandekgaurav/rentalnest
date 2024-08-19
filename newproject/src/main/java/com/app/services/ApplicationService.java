
package com.app.services;

import java.util.List;

import com.app.dto.ApplicationDTO;

public interface ApplicationService {
	ApplicationDTO createApplication(ApplicationDTO applicationDTO);

	ApplicationDTO updateApplication(int applicationId, ApplicationDTO applicationDTO);

	ApplicationDTO getApplicationById(int applicationId);

	void deleteApplication(int applicationId);

	List<ApplicationDTO> getAllApplications();

	List<ApplicationDTO> getApplicationsByProperty(int propertyId);

	List<ApplicationDTO> getApplicationsByTenant(int tenantId);
	//List<ApplicationDTO> getApplicationsByLandlord(int landlordId); 

	ApplicationDTO updateApplicationStatus(int id, ApplicationDTO applicationDTO);
}
