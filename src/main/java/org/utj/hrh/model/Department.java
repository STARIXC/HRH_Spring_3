package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private int dept_id;
	@Column(name = "dept_name")
	private String dept_name;
	@Column(name = "dept_desc")
	private String dept_desc;

	
	
}
