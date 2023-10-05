package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.Document;

public interface DocumentRepository extends JpaRepository<Document,Long> {

}
