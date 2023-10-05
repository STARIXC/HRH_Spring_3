package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity,Long> {

}
