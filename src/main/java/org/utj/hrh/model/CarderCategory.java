package org.utj.hrh.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "carder_category")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarderCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "carder_category_name")
	private String carderCategoryName;
	@Column(name = "hrs_per_week")
	private String hrsPerWeek;
	@Column(name = "createdAt")
	private LocalDateTime dateCreated;
	@Column(name = "updatedAt")
	private LocalDateTime lastUpdated;
	
	@OneToMany(mappedBy = "carderCategory")
	private List<Designation> designations ;
	
	@Override
	public String toString() {
		return "CarderCategory{" +
				"id=" + id +
				", carderCategoryName='" + carderCategoryName + '\'' +
				", hrsPerWeek='" + hrsPerWeek + '\'' +
				", dateCreated=" + dateCreated +
				", lastUpdated=" + lastUpdated +
				'}';
	}
}
