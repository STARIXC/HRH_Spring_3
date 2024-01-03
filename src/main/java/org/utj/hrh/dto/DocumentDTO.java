package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
	private Integer id;
	private String docID;
	private Long employeeId; // Replacing the direct Employee reference
	private String documentValue;
	private String createdBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Integer documentTypeId; // Replacing the direct DocumentType reference
	
}
