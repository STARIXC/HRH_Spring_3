package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Admin;
import org.utj.hrh.model.User;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Admin findByAdmin_id(Integer user_id); // Implement a method to fetch admin data by user
    Optional<Admin> findByPersonPersonNumber(String personNumber); // Add this method

}