package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Document;
@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer> {

    Long countById(Integer id);
}
