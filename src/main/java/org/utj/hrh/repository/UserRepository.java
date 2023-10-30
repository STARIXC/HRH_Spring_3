package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);


    @Query("SELECT u, r FROM User u JOIN Role r ON r.id = u.role.id WHERE u.username = :username")
    User setAuthoritiesByUsername(@Param("username") String username);
}
