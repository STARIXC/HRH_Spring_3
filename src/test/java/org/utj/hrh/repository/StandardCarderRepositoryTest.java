package org.utj.hrh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.utj.hrh.model.StandardCarder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardCarderRepositoryTest {
    @Autowired
    private StandardCarderRepository standardCarderRepository;
    @Test
    void getStandardCarderByCarder_category_id() {
        // Arrange
        Integer carderCategoryId = 1; // Replace with a valid carder category ID from your test data

        // Act
//       List<StandardCarder> standardCarder = standardCarderRepository.get(carderCategoryId);

        // Assert
//        assertNotNull(standardCarder);
        // Add more assertions as needed to verify the properties of the retrieved entity


    }
}