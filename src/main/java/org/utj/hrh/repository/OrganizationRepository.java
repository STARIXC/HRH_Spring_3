package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.License;
import org.utj.hrh.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {

}
