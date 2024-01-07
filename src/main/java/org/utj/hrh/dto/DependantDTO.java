package org.utj.hrh.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.utj.hrh.model.Employee;

import java.time.LocalDate;

@Data
public class DependantDTO {
	private Long id;
	private String name;
	private String relationship;
	private LocalDate dateOfBirth;
	private Long employeeDependant;

}
