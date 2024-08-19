package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApplicationDTO;
import com.app.services.ApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*") 
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create")
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationDTO applicationDTO) {
        ApplicationDTO createdApplication = applicationService.createApplication(applicationDTO);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable int id, @RequestBody ApplicationDTO applicationDTO) {
        ApplicationDTO updatedApplication = applicationService.updateApplication(id, applicationDTO);
        return ResponseEntity.ok(updatedApplication);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable int id) {
        ApplicationDTO application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }
    @PutMapping("/update-status/{id}")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(@PathVariable int id, @RequestBody ApplicationDTO applicationDTO) {
        ApplicationDTO updatedApplication = applicationService.updateApplicationStatus(id, applicationDTO);
        return ResponseEntity.ok(updatedApplication);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable int id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getallapplications")
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        List<ApplicationDTO> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByProperty(@PathVariable int propertyId) {
        List<ApplicationDTO> applications = applicationService.getApplicationsByProperty(propertyId);
        return ResponseEntity.ok(applications);
    }



    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByTenant(@PathVariable int tenantId) {
        List<ApplicationDTO> applications = applicationService.getApplicationsByTenant(tenantId);
        return ResponseEntity.ok(applications);
    }
}
