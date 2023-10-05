package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "holidays") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	@Column(name = "holiday_name")
    private String holiday_name;
	@Column(name = "start_date")
    private Date start_date;
	@Column(name = "end_date")
    private Date end_date;
	@Column(name = "no_of_days")
    private String no_of_days;
	@Column(name = "comment")
    private String comment;


}
