
package org.utj.hrh.model;

/**
 *
 * @author UTJ
 */
public class StandardCarder {
    int id, carder_category_id,  emp_type;
    String standardized_cadre_name,carder_category_name,emp_type_name;

    public String getCarder_category_name() {
        return carder_category_name;
    }

    public void setCarder_category_name(String carder_category_name) {
        this.carder_category_name = carder_category_name;
    }

    public String getEmp_type_name() {
        return emp_type_name;
    }

    public void setEmp_type_name(String emp_type_name) {
        this.emp_type_name = emp_type_name;
    }

    public StandardCarder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarder_category_id() {
        return carder_category_id;
    }

    public void setCarder_category_id(int carder_category_id) {
        this.carder_category_id = carder_category_id;
    }

    public int getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(int emp_type) {
        this.emp_type = emp_type;
    }

    public String getStandardized_cadre_name() {
        return standardized_cadre_name;
    }

    public void setStandardized_cadre_name(String standardized_cadre_name) {
        this.standardized_cadre_name = standardized_cadre_name;
    }

    @Override
    public String toString() {
        return "StandardCarder{" + "id=" + id + ", carder_category_id=" + carder_category_id + ", emp_type=" + emp_type + ", standardized_cadre_name=" + standardized_cadre_name + ", carder_category_name=" + carder_category_name + ", emp_type_name=" + emp_type_name + '}';
    }


    
    
}
