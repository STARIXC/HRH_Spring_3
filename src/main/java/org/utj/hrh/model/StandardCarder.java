
package org.utj.hrh.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UTJ
 */
@Entity
@Table(name = StandardCarder.TABLE_NAME)
public class StandardCarder {
    public static final String TABLE_NAME = "standardized_cadre";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 40, nullable = false)
    private String standardized_cadre_name;
    @Column(length = 3, nullable = false)
    private Integer carder_category_id;

    @Column(length = 3, nullable = false)
    private Integer carder_type_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "standardized_cadre_id", referencedColumnName = "id")
    List< Designation > designations = new ArrayList< >();


    public StandardCarder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStandardized_cadre_name() {
        return standardized_cadre_name;
    }

    public void setStandardized_cadre_name(String standardized_cadre_name) {
        this.standardized_cadre_name = standardized_cadre_name;
    }

    public Integer getCarder_category_id() {
        return carder_category_id;
    }

    public void setCarder_category_id(Integer carder_category_id) {
        this.carder_category_id = carder_category_id;
    }

    public Integer getCarder_type_id() {
        return carder_type_id;
    }

    public void setCarder_type_id(Integer carder_type_id) {
        this.carder_type_id = carder_type_id;
    }

    public List<Designation> getDesignations() {
        return designations;
    }

    public void setDesignations(List<Designation> designations) {
        this.designations = designations;
    }

    @Override
    public String toString() {
        return "StandardCarder{" +
                "standardized_cadre_name='" + standardized_cadre_name + '\'' +
                ", carder_category_id=" + carder_category_id +
                ", carder_type_id=" + carder_type_id +
                '}';
    }

    
    
}
