package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
