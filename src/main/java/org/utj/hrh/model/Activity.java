package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activity_id;
    @Column(name = "activity_description")
    private String activity_description;
    @Column(name = "activity_cadre_type")
    private int activity_cadre_type;
    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "updatedAt")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;


}
