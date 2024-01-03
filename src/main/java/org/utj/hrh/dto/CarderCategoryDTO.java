package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CarderCategoryDTO {
	private Integer id;
	private String carderCategoryName;
	private String hrsPerWeek;
	private LocalDateTime dateCreated;
	private LocalDateTime lastUpdated;
	// List of DesignationDTO might be included based on your needs
}
