package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.License;
import org.utj.hrh.model.Organization;
import org.utj.hrh.repository.LicenseRepository;
import org.utj.hrh.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService( OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;



    }
    // Method to retrieve organization information
    public Organization getOrganization() {
        Optional<Organization> organizationOptional = organizationRepository.findById(1L); // Assuming 1 is the organization ID
        return organizationOptional.orElse(new Organization()); // Return an empty Organization if not found
    }

    // Method to save organization information
    public void saveOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

}
