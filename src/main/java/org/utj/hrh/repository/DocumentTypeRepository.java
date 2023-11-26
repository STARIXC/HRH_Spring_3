package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.model.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType,Integer> {
    @Query("SELECT d from DocumentType d where d.name =:name ")
    DocumentType getDocumentTypeByName(@Param("name") String name);

    long countById(Integer id);
}
